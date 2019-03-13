// получение списка стран
ready = () => $.ajax({
    url: "https://restcountries.eu/rest/v2/all",
    dataType: "json",
    success: function (data) {
        s = $("#reg-select-country");
        data.forEach(function (x) {
            code = x.alpha2Code.toLowerCase();
            selected = "";
            s.append("<option value=\"" + code + "\""+ selected + ">" + x.name + "</option>");
        });
    }
});
document.addEventListener("DOMContentLoaded", ready);