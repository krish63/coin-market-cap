package utils.API;


public class APIEndpoints {
    public String getCryptoMap() {
        return "/v1/cryptocurrency/map";
    }

    public String getCryptoLatestInfo(String limit) {
        return "/v1/cryptocurrency/listings/latest?start=1&limit=" + limit;
    }


    public String getFiatMap() {
        return "/v1/fiat/map";
    }


    public String priceConversion(String amount, String currencyId, String cryptoId) {

        return "/v2/tools/price-conversion?amount=" + amount + "&convert_id=" + currencyId + "&id=" + cryptoId;
    }

    public String getCryptoInfo(String id) {
        return "/v2/cryptocurrency/info?id=" + id;
    }
}
