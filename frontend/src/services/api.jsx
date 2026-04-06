/**
 * Sends a calculation request to the backend API.
 * Includes evaluation context such as angle mode (RAD/DEG)
 * and numeric precision for the result.
 *
 * @param {string} input - Mathematical expression to evaluate
 * @param {"RAD"|"DEG"} angleMode - Angle mode for trigonometric functions
 * @param {number} precision - Number of decimal places (0–10)
 * @returns {Promise<{ result: string }>} Parsed JSON response from the API
 */
export async function calculate(input, angleMode, precision) {
    const res = await fetch("/calculator/parse", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            input,
            angleMode,
            precision
        })
    });
    return await res.json();
}

/**
 * Converts a displayed result to its opposite representation (fraction <-> decimal).
 *
 * @param {string} result - Current result value displayed in the calculator
 * @param {number} precision - Precision used when converting fraction to decimal
 * @returns {Promise<{ result: string }>} Parsed JSON response from the API
 */
export async function convertResult(result, precision) {
    const res = await fetch("/calculator/convert-result", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            result,
            precision
        })
    });

    return await res.json();
}
