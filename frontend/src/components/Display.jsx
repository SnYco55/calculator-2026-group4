/**
 * Displays the current expression and the calculated result.
 * @param {Object} props
 * @param {string[]} props.tokens - List of input tokens
 * @param {string} props.result - Computed result
 */
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