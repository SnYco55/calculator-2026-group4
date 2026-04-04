import { useState } from "react";
import Display from "./Display";
import Keypad from "./Keypad";
import Help from "./Help";
import { calculate } from "../services/api";
import SettingsBar from "./SettingsBar";


/**
 * Main calculator component.
 * Manages user input, displays results, and handles API calculation.
 */
export default function Calculator() {
    const [tokens, setTokens] = useState([]);
    const [result, setResult] = useState("");
    const [showHelp, setShowHelp] = useState(false);

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
    const add = (t) => setTokens((prev) => [...prev, t]);


    const clear = () => {
        setTokens([]);
        setResult("");
    };


    const del = () => {
        setTokens((prev) => prev.slice(0, -1));
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
                setResult(res.result);
            } else {
                setResult("API Error");
            }
        } catch (err) {
            console.log(err)
            setResult("Error");
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
                <SettingsBar onSettingsChange={handleSettingsChange} />
                <Keypad add={add} clear={clear} del={del} compute={compute} />
            </div>

            {/* PANEL SLIDE */}
            <div className={`help-drawer ${showHelp ? "open" : ""}`}>
                <Help />
            </div>

        </div>
    );
}