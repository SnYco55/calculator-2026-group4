export default function Keypad({ add, clear, del, compute }) {

    const buttons = [
        "7","8","9","/","π",
        "4","5","6","*","e",
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