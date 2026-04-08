import {ArrowLeftRight} from "lucide-react";

/**
 * Help component that displays usage examples and supported expression syntax.
 */
export default function Help() {
    const examples = [
        { desc: "Arithmetic", syntax: "(2+3)*4-5" },
        { desc: "Fractions", syntax: "(1/3)+(5/2)" },
        { desc: "Powers", syntax: "2^3" },
        { desc: "Scientific notation", syntax: "2.00E10" },
        { desc: "Square root", syntax: "sqrt(16)" },
        { desc: "Complex numbers", syntax: "3+5i" },
        { desc: "Logarithm", syntax: "log(5)" },
        { desc: "Trig in RAD", syntax: "sin(π/2)" },
        { desc: "Trig in DEG", syntax: "sin(90)" },
    ];


    return (
        <div className="help-container">
            <h3>User Guide</h3>
            <p className="help-intro">
                Enter an expression, choose your settings, then press =.
            </p>

            <ul className="help-list">
                {examples.map((item) => (
                    <li key={`example-${item.desc}`}>
                        <span className="help-desc">{item.desc}</span>
                        <code className="help-syntax">{item.syntax}</code>
                    </li>
                ))}
            </ul>
            <div className="help-convert-row">
                <span className="help-desc help-convert-text">Toggles fraction/decimal display.</span>
                <span className="btn-convert help-convert-btn">
                    S <ArrowLeftRight size={12} /> D
                </span>
            </div>
        </div>
    );
}
