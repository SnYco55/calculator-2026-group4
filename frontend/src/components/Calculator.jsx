import { useState } from "react";
import Display from "./Display";
import Keypad from "./Keypad";
import Help from "./Help";
import { calculate } from "../services/api";

export default function Calculator() {
    const [tokens, setTokens] = useState([]);
    const [result, setResult] = useState("");
    const [showHelp, setShowHelp] = useState(false);

    const add = (t) => setTokens((prev) => [...prev, t]);

    const clear = () => {
        setTokens([]);
        setResult("");
    };

    const del = () => {
        setTokens((prev) => prev.slice(0, -1));
    };

    const compute = async () => {
        const expr = tokens.join("");
        if (!expr.trim()) return;

        try {
            const res = await calculate(expr);

            if (res && typeof res === 'object') {
                if (res.result !== undefined) {
                    setResult(String(res.result));
                } else {
                    setResult("API Error");
                }
            } else {
                setResult(String(res));
            }
        } catch (err) {
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
                <Keypad add={add} clear={clear} del={del} compute={compute} />
            </div>

            {/* PANEL SLIDE */}
            <div className={`help-drawer ${showHelp ? "open" : ""}`}>
                <Help />
            </div>

        </div>
    );
}