const API_URL = "http://localhost:8080/api/v1/country";

async function fetchCountries() {
  const response = await fetch(API_URL);
  if (!response.ok) {
    throw new Error("Failed to fetch countries");
  }
  return response.json();
}

async function CountryService() {
  try {
    const data = await fetchCountries();
    return data;
  } catch (error) {
    console.error("Error fetching countries:", error);
    throw error;
  }
}

export default CountryService;
