export default function Display({ tokens, result }) {
    return (
        <div className="display">
            <div className="expression">
                {tokens.join("") || "0"}
            </div>
            <div className="result">
                {result}
            </div>
        </div>
    );
}