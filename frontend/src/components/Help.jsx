export default function Help() {
    const examples = [
        { desc: "Arithmetic", syntax: "2+3" },
        { desc: "Fractions", syntax: "(1/3)+(5/2)" },
        { desc: "Complex Numbers", syntax: "2+3i" },
        { desc: "Trigonometry", syntax: "sin(45)" },
        { desc: "Square Roots", syntax: "sqrt(16)" }
    ];

    return (
        <div className="help-container">
            <h3>User Guide</h3>
            <p className="help-intro">
                Enter your expressions and press equals to compute.
            </p>
            <ul className="help-list">
                {examples.map((item, index) => (
                    <li key={index}>
                        <span className="help-desc">{item.desc}</span>
                        <code className="help-syntax">{item.syntax}</code>
                    </li>
                ))}
            </ul>
        </div>
    );
}
