import { calculate, convertResult } from "./services/api";

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

describe("convertResult", () => {

  beforeEach(() => {
    fetch.mockClear();
  });

  it("should send the correct request and return parsed JSON result", async () => {
    const mockResponse = { result: "1/2" };

    fetch.mockResolvedValueOnce({
      json: jest.fn().mockResolvedValueOnce(mockResponse)
    });

    const result = "0.5";
    const precision = 6;

    const converted = await convertResult(result, precision);

    expect(fetch).toHaveBeenCalledWith("/calculator/convert-result", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        result,
        precision
      })
    });

    expect(converted).toEqual(mockResponse);
  });
});
