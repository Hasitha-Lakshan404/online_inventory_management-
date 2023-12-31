var baseUrl = "http://localhost:8080/api/v1/"
var iId;
let des; //description
let it; //item


$(window).on('load', function () {

    $("#addItemForm").validate({
        rules: {
            addItemName: {
                required: true
            },
            addItemType: {
                required: true
            },
            addItemQuantity: {
                required: true,
                digits: true
            },
            addItemUnitPrice:  {
                required: true,
                number: true
            },
            addItemDescription: {
                required: true
            }
        },
        messages: {
            addItemName: {
                required:"Please enter Item Name"
            },
            addItemType: {
                required:"Please enter Item Type"
            },
            addItemQuantity: {
                required: "Please enter  Quantity",
                digits: "Please enter Valid Quantity"
            },
            addItemUnitPrice: {
                required: "Please enter Unit Price",
                number: "Please enter Valid Unit Price"
            },
            addItemDescription:{
                required:"Please enter Item Description"
            }
        },
        errorElement: "p",
        errorClass: "text-danger",
        highlight: function (element) {
            $(element).addClass("is-invalid");
        },
        unhighlight: function (element) {
            $(element).removeClass("is-invalid");
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element);
        }
    });

    $('#tblTbl').DataTable({
        language: {
            emptyTable: "No Data Available",
            info: "Showing _START_ to _END_ of _TOTAL_ entries",
            infoEmpty: "Showing 0 to 0 of entries",
            lengthMenu: "show _MENU_ entries",
            search: "Search:",
            zeroRecords: "No matching records found"
        }
    })

})

loadItemData();

$('#btnSaveAddItem').click(function () {
    duplicateChecker();

})


function setAutoDescription() {
    if ($('#addItemDescription').val() != "") {
        return $('#addItemDescription').val();
    } else {
        return "No Description"
    }
}


/*Save*/
function saveItem() {

    des = setAutoDescription();

    var item = {
        itemName: $('#addItemName').val(),
        itemType: $('#addItemType').val(),
        quantity: $('#addItemQuantity').val() + " " + $('#qtyDropdown').val(),
        unitPrice: $('#addItemUnitPrice').val(),
        description: des
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
                loadItemData();
            }
            loadItemData();
            clearItemInputFields();
        },
        error: function (err) {
            console.log(err)
        }
    });
}

/*Load All Items*/
function loadItemData() {
    var setAr = new Set();

    $("#itemTable").empty();
    $("#findItemByTypeDropdown").empty();


    $.ajax({
        url: baseUrl + "item/" + "allItems",
        method: "GET",
        success: function (resp) {
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


                setAr.add(item.itemType);


                $("#itemTable").append(row);

                $("#itemTable").off("click");
                $("#itemTable").click(function () {

                });
            }
            for (const el of setAr) {
                var dropDta = `<option>${el}</option>`;
                $("#findItemByTypeDropdown").append(dropDta);
            }

            //for bind the table row click event/when the row click value set the input fields
            bindRowClickEvents();
        }
    });
}


function bindRowClickEvents() {
    $("#itemTable>tr").click(function () {

        var qty = $(this).children(":eq(4)").text().split(" ");

        iId = $(this).children(":eq(0)").text();
        $('#addItemName').val($(this).children(":eq(1)").text());
        $('#addItemType').val($(this).children(":eq(2)").text());
        $('#addItemQuantity').val(qty[0]);
        $('#qtyDropdown').val(qty[1]);
        $('#addItemDescription').val($(this).children(":eq(3)").text());
        $('#addItemUnitPrice').val($(this).children(":eq(5)").text());

    });
}


/*Update*/
$("#btnUpdateAddItem").click(function () {
    des = setAutoDescription();

    var itm = {
        itemId: iId,
        itemName: $('#addItemName').val(),
        itemType: $('#addItemType').val(),
        quantity: $('#addItemQuantity').val() + " " + $('#qtyDropdown').val(),
        unitPrice: $('#addItemUnitPrice').val(),
        description: des
    }
    itemUpdate(itm);
})

function itemUpdate(itm) {
    $.ajax({
        url: baseUrl + 'item',
        method: 'put',
        contentType: "application/json",
        data: JSON.stringify(itm),
        success: function (res) {
            // alert(res.message);
            loadItemData();
            clearItemInputFields();
        }, error: function (error) {
            var jsObject = JSON.parse(error.responseText);
            alert(jsObject.message);
        }
    });
}

/*Delete*/
$("#btnDeleteAddItem").click(function () {
    deleteItem();
})

function deleteItem() {
    $.ajax({
        url: baseUrl + "item?id=" + iId,
        method: "delete",
        success(resp) {
            loadItemData();
            clearItemInputFields();
        }
    });
}

/*Clear*/
function clearItemInputFields() {
    $('#addItemName,#addItemType,#addItemQuantity,#qtyDropdown,#addItemUnitPrice,#addItemDescription').val("");
}


/*New Button*/
$("#btnNewAddItem").click(function () {
    clearItemInputFields();
})


/*function validate*/

/*item duplicate add validate*/
function isItemDuplicate(callback) {
    $.ajax({
        url: baseUrl + "item/" + "allItems",
        method: "GET",
        success: function (resp) {
            for (const item of resp.data) {
                if (item.itemName === $('#addItemName').val()) {
                    it = item;
                    callback(true);
                    return;
                }
            }
            callback(false);
        }
    });
}

function duplicateChecker() {
    isItemDuplicate(function (isDuplicate) {

        if (isDuplicate) { //if item name already exists
            console.log(it);

            var qty = it.quantity.split(" ");
            let totalQty = parseInt(qty[0]) + parseInt($('#addItemQuantity').val())

            var newItem = {
                itemId: it.itemId,
                itemName: it.itemName,
                itemType: it.itemType,
                quantity: totalQty + " " + qty[1],
                unitPrice: it.unitPrice,
                description: it.description
            }

            itemUpdate(newItem);

        } else {
            saveItem();
        }


    });
}

/*Search | Sort*/

$('#searchInput').keypress(function (event) {
    if (event.which === 13) { //track the "ENTER" key
        const searchWord = $(this).val();
        searchAndSort(searchWord);
    }
});

$("#findItemByTypeDropdown").change(function () {
    // Event handler code here
    var selectedValue = $(this).val();
    // console.log("Selected option: " + selectedValue);
    searchAndSort(selectedValue);

});

function searchAndSort(keyword) {

    $("#itemTable").empty();

    $.ajax({
        url: baseUrl + `item/searchItem?val=${encodeURIComponent(keyword)}`,
        method: "GET",
        success: function (resp) {
            for (const item of resp.data) {
                let div = `<tr>
            <td>${item.itemId}</td>
            <td>${item.itemName}</td>
            <td>${item.itemType}</td>
            <td>${item.description}</td>
            <td>${item.quantity}</td>
            <td>${item.unitPrice}</td>
           </tr>`;
                $("#itemTable").append(div);
                bindRowClickEvents();

            }
        }
    });
}