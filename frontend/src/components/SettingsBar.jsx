import { useState } from "react";
import { ArrowLeftRight } from 'lucide-react';


/**
 * Settings bar handling:
 * - Angle mode (RAD / DEG)
 * - Precision input
 * - Result representation toggle (Fraction <-> Decimal)
 */
export default function SettingsBar({ onSettingsChange, onToggleResultFormat, precisionChanged, cachedFraction }) {
    const [angleMode, setAngleMode] = useState("RAD");
    const [precision, setPrecision] = useState(2);

    const updateAngleMode = (mode) => {
        setAngleMode(mode);
        onSettingsChange({ angleMode: mode, precision });
    };

    const updatePrecision = (value) => {
        const p = parseInt(value) || 0;
        if (value > 10){setPrecision(10)}
        else if(value < 0){setPrecision(0)}
        else{setPrecision(p);}
        onSettingsChange({ angleMode, precision: p });
    };

    return (
        <div className="settings-bar">
            {/* PRECISION INPUT */}
            <div className="precision-input">
                <span className="precision-label">Precision</span>

                <div className="number-control">
                    <input
                        type="number"
                        min="0"
                        max="10"
                        value={precision}
                        onChange={(e) => {
                            const val = e.target.value.replace(/\D/g, "");
                            updatePrecision(val);
                        }}
                    />

                    <div className="arrows">
                        <button onClick={() => updatePrecision(Number(precision) + 1)}>▲</button>
                        <button onClick={() => updatePrecision(Number(precision) - 1)}>▼</button>
                    </div>
                </div>
            </div>

            {/* ANGLE SWITCH */}
            <div
                className={`angle-switch ${angleMode === "DEG" ? "deg" : ""}`}
                onClick={() =>
                    updateAngleMode(angleMode === "RAD" ? "DEG" : "RAD")
                }
            >
                <div className="angle-slider"></div>

                <div className={`angle-option ${angleMode === "RAD" ? "active" : ""}`}>
                    <b>RAD</b>
                </div>
                <div className={`angle-option ${angleMode === "DEG" ? "active" : ""}`}>
                    <b>DEG</b>
                </div>
            </div>

            <button
                className="btn-convert"
                onClick={onToggleResultFormat}
                disabled={!cachedFraction || precisionChanged}
                style={{ 
                    display: 'flex', 
                    alignItems: 'center', 
                    gap: '4px', 
                    width:"76px", 
                    opacity: (!cachedFraction || precisionChanged) ? 0.5 : 1,
                    cursor: (!cachedFraction || precisionChanged) ? 'not-allowed' : 'pointer'
                }}
            >
                S <ArrowLeftRight size={12} /> D
            </button>


        </div>
    );
}