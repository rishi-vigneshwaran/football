import { urls, apiVersion, localhost } from "./../constants/url.constants";

const API_URL = localhost + apiVersion + urls.country;

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
