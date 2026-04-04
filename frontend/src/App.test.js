import { calculate } from "./services/api";

global.fetch = jest.fn();

describe("calculate", () => {

  beforeEach(() => {
    fetch.mockClear();
  });

  it("should send the correct request and return parsed JSON result", async () => {
    const mockResponse = { result: 42 };

    fetch.mockResolvedValueOnce({
      json: jest.fn().mockResolvedValueOnce(mockResponse)
    });

    const input = "2+2";
    const angleMode = "DEG";
    const precision = 2;

    const result = await calculate(input, angleMode, precision);

    expect(fetch).toHaveBeenCalledWith("/calculator/parse", {
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

    expect(result).toEqual(mockResponse);
  });

});