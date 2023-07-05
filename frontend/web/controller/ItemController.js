
var baseUrl="http://localhost:8080/api/v1/"

$('#btnSaveAddItem').click(function () {
    saveItem();
})


function saveItem() {

    var item = {
        itemName : $('#addItemName').val(),
        itemType : $('#addItemType').val(),
        quantity : $('#addItemQuantity').val()+" "+$('#qtyDropdown').val(),
        unitPrice : $('#addItemUnitPrice').val(),
        description : $('#addItemDescription').val()
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

        },
        error: function (err) {
            console.log(err)
        }
    });
}