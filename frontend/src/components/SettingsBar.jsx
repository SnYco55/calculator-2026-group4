import { useState } from "react";

/**
 * Settings bar handling:
 * - Angle mode (RAD / DEG)
 * - Precision input
 */
export default function SettingsBar({ onSettingsChange }) {
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
                    setAngleMode(angleMode === "RAD" ? "DEG" : "RAD")
                }
            >
                <div className="angle-slider"></div>

                <div className={`angle-option ${angleMode === "RAD" ? "active" : ""}`}>
                    RAD
                </div>
                <div className={`angle-option ${angleMode === "DEG" ? "active" : ""}`}>
                    DEG
                </div>
            </div>


        </div>
    );
}