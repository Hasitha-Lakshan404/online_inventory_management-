var baseUrl = "http://localhost:8080/api/v1/"

$(window).on('load', function () {
   loadItemData();
})





$('#btnSaveAddItem').click(function () {
    saveItem();
})

/*Save*/
function saveItem() {

    var item = {
        itemName: $('#addItemName').val(),
        itemType: $('#addItemType').val(),
        quantity: $('#addItemQuantity').val() + " " + $('#qtyDropdown').val(),
        unitPrice: $('#addItemUnitPrice').val(),
        description: $('#addItemDescription').val()
    }
    console.log(item);
    $.ajax({
        url: baseUrl + "item",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(item),
        success: function (res) {
            if (res.status == 200) {
                alert(res.message);
            }
            loadItemData();
        },
        error: function (err) {
            console.log(err)
        }
    });
}

/*Load All Items*/
function loadItemData() {
    $("#itemTable").empty();

    $.ajax({
        url: baseUrl + "item/" + "allItems",
        method: "GET",
        success: function (resp){
            console.log(resp)
            for (const item of resp.data) {

                let row = `<tr>
            <td>${item.itemId}</td>
            <td>${item.itemName}</td>
            <td>${item.itemType}</td>
            <td>${item.description}</td>
            <td>${item.quantity}</td>
            <td>${item.unitPrice}</td>
           </tr>`;
                $("#itemTable").append(row);

                $("#itemTable").off("click");
                $("#itemTable").click(function () {

                });
            }
        }
    });
}