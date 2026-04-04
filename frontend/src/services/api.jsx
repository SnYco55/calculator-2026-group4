export async function calculate(input) {
    const res = await fetch("/calculator/parse", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            input: input
        }),
    });

    return await res.json();
}