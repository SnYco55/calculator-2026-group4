import { useState } from "react";
import Display from "./Display";
import Keypad from "./Keypad";
import Help from "./Help";
import { calculate, convertResult } from "../services/api";
import SettingsBar from "./SettingsBar";


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
    const [currentDisplayMode, setCurrentDisplayMode] = useState("original"); // "original" or "toggled"

    const [settings, setSettings] = useState({
        angleMode: "RAD",
        precision: 2
    });

    const handleSettingsChange = (newSettings) => {
        setSettings(newSettings);
    };

    /**
     * Adds a token (number or operator) to the expression.
     * @param {string} t - Token to add
     */
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
     * Handles errors and updates the result state.
     * @returns {Promise<void>}
     */
    const compute = async () => {
        const expr = tokens.join("");
        if (!expr.trim()) return;

        try {
            const res = await calculate(expr, settings.angleMode, settings.precision);

            if (res.result !== undefined) {
                let finalResult = res.result;

                const isFraction = /^[+-]?\d+\s*\/\s*[+-]?\d+$/.test(res.result);

                if (!isFraction) {
                    try {
                        const fractionRes = await convertResult(res.result, settings.precision);
                        if (fractionRes.result !== undefined) {
                            const fractionMatch = fractionRes.result.match(/^([+-]?\d+)\s*\/\s*([+-]?\d+)$/);
                            const denominator = fractionMatch ? Math.abs(Number(fractionMatch[2])) : Number.POSITIVE_INFINITY;

                            finalResult = fractionRes.result;

                        }
                    } catch (err) {
                        console.log("Could not convert to fraction:", err);
                    }
                }

                setResult(finalResult);
                setCachedFraction(finalResult);
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

        if (cachedFraction) {
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
        }
    };


    return (
        <div className="app-wrapper">

            {/* BOUTON HELP */}
            <div className={`help-tab ${showHelp ? "open" : ""}`} onClick={() => setShowHelp(!showHelp)}>
                H<br/>E<br/>L<br/>P
            </div>

            {/* CALCULATRICE */}
            <div className="calculator">
                <Display tokens={tokens} result={result} />
                <SettingsBar
                    onSettingsChange={handleSettingsChange}
                    onToggleResultFormat={toggleFractionDecimal}
                />
                <Keypad add={add} clear={clear} del={del} compute={compute} />
            </div>

            {/* PANEL SLIDE */}
            <div className={`help-drawer ${showHelp ? "open" : ""}`}>
                <Help />
            </div>

        </div>
    );
}