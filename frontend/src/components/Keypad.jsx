/**
 * Keypad component that renders calculator buttons and handles user interactions.
 * It sends input tokens to the parent component and triggers actions like clear, delete, and compute.
 * @param {Object} props
 * @param {function(string): void} props.add - Adds a token to the expression
 * @param {function(): void} props.clear - Clears the entire input
 * @param {function(): void} props.del - Deletes the last token
 * @param {function(): Promise<void>} props.compute - Computes the current expression
 */
export default function Keypad({ add, clear, del, compute }) {

    const buttons = [
        "7","8","9","/","π",
        "4","5","6","*","E",
        "1","2","3","-","AC",
        "0",".","sqrt(","+","DEL",
        "(",")","log(","^","=",
        "sin(","cos(","tan(","i",""
    ];

    const handleClick = (b) => {
        if (b === "AC") return clear();
        if (b === "DEL") return del();
        if (b === "=") return compute();

        add(b);
    };

    return (
        <div className="grid">
            {buttons.map((b, i) => (
                b === "" ? (
                    <div key={i}></div>
                ) : (
                    <button
                        key={i}
                        onClick={() => handleClick(b)}
                        className={
                            b === "=" ? "btn-equals" :
                                b === "AC" || b === "DEL" ? "btn-action" :
                                    ""
                        }
                    >
                        {b}
                    </button>
                )
            ))}
        </div>
    );
}