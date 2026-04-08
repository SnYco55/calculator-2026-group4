import { useState } from "react";
import Display from "./Display";
import Keypad from "./Keypad";
import Help from "./Help";
import { calculate, convertResult } from "../services/api";
import SettingsBar from "./SettingsBar";

/**
 * Checks if a fraction is acceptable based on denominator size and precision.
 * @param {string|number} decimal
 * @param {string} fractionStr
 * @param {number} maxDen
 * @param {number} tolerance
 * @returns {boolean}
 */
function isNiceFraction(decimal, fractionStr, maxDen = 500, tolerance = 1e-9) {
    const match = fractionStr.match(/^([+-]?\d+)\s*\/\s*([+-]?\d+)$/);
    if (!match) return false;

    const num = Number(match[1]);
    const den = Number(match[2]);

    if (den === 0) return false;
    if (Math.abs(den) > maxDen) return false;

    const approx = num / den;
    if (Math.abs(approx - Number(decimal)) > tolerance) return false;

    if (den % 10 === 0 && den >= 1000) return false;

    return true;
}

/**
 * Main calculator component.
 * Manages user input, displays results, and handles API calculation.
 */
export default function Calculator() {
    const [tokens, setTokens] = useState([]);
    const [result, setResult] = useState("");
    const [justComputed, setJustComputed] = useState(false);
    const [showHelp, setShowHelp] = useState(false);
    const [cachedFraction, setCachedFraction] = useState("");
    const [currentDisplayMode, setCurrentDisplayMode] = useState("original");

    const [settings, setSettings] = useState({
        angleMode: "RAD",
        precision: 2
    });
    const [precisionChanged, setPrecisionChanged] = useState(false);

    const handleSettingsChange = (newSettings) => {
        if (newSettings.precision !== settings.precision) {
            setPrecisionChanged(true);
        }
        setSettings(newSettings);
    };

    const add = (t) => {
        if (justComputed) {
            setTokens([t]);
            setResult("");
            setCachedFraction("");
            setCurrentDisplayMode("original");
            setJustComputed(false);
            return;
        }

        setTokens((prev) => [...prev, t]);
    };

    const clear = () => {
        setTokens([]);
        setResult("");
        setCachedFraction("");
        setCurrentDisplayMode("original");
        setJustComputed(false);
    };

    const del = () => {
        setTokens((prev) => prev.slice(0, -1));
        setJustComputed(false);
    };

    /**
     * Computes the result of the current expression using an API.
     * @returns {Promise<void>}
     */
    const compute = async () => {
        const expr = tokens.join("");
        if (!expr.trim()) return;

        setPrecisionChanged(false);
        try {
            const res = await calculate(expr, settings.angleMode, settings.precision);

            if (res.result !== undefined) {
                let finalResult = res.result;

                const isFraction = /^[+-]?\d+\s*\/\s*[+-]?\d+$/.test(res.result);

                if (!isFraction) {
                    try {
                        const fractionRes = await convertResult(res.result, settings.precision);
                        if (fractionRes.result !== undefined) {
                            const fractionStr = fractionRes.result;
                            const dynamicMaxDen =
                                Math.abs(Number(res.result)) > 10 ? 200 : 1000;

                            if (isNiceFraction(res.result, fractionStr, dynamicMaxDen)) {
                                finalResult = fractionStr;
                                setCachedFraction(fractionStr);
                            } else {
                                finalResult = res.result;
                                setCachedFraction("");
                            }
                        }
                    } catch (err) {
                        console.log("Could not convert to fraction:", err);
                        setCachedFraction("");
                    }
                } else {
                    setCachedFraction(res.result);
                }

                setResult(finalResult);
                setCurrentDisplayMode("original");
                setJustComputed(true);
            } else {
                setResult("API Error");
                setJustComputed(true);
            }
        } catch (err) {
            console.log(err)
            setResult("Error");
            setJustComputed(true);
        }
    };

    const toggleFractionDecimal = async () => {
        if (!result || result === "Error" || result === "API Error") return;
        if (!cachedFraction) return;

        if (currentDisplayMode === "original") {
            try {
                const res = await convertResult(cachedFraction, settings.precision);
                if (res.result !== undefined) {
                    setResult(res.result);
                    setCurrentDisplayMode("toggled");
                } else {
                    setResult("API Error");
                }
            } catch (err) {
                console.log(err);
                setResult("Error");
            }
        } else {
            setResult(cachedFraction);
            setCurrentDisplayMode("original");
        }
    };

    return (
        <div className="app-wrapper">

            <div className={`help-tab ${showHelp ? "open" : ""}`} onClick={() => setShowHelp(!showHelp)}>
                H<br/>E<br/>L<br/>P
            </div>

            <div className="calculator">
                <Display tokens={tokens} result={result} />
                <SettingsBar
                    onSettingsChange={handleSettingsChange}
                    onToggleResultFormat={toggleFractionDecimal}
                    precisionChanged={precisionChanged}
                    cachedFraction={cachedFraction}
                />
                <Keypad add={add} clear={clear} del={del} compute={compute} />
            </div>

            <div className={`help-drawer ${showHelp ? "open" : ""}`}>
                <Help />
            </div>

        </div>
    );
}