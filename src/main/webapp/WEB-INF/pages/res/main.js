function send_data()
{
    var message = {};
    message.firstName = "ffffff";

    $.ajax({
        url: "/api/getUsers",
        method : "POST",
        data: message,
        success: function(result) {
            createTable(result);
        }
    });
}

function createTable(arg){
    var result = JSON.parse(arg);
    var rows = result.rows;
    var header = '<tr><td>first name</td><td>second name</td><td>city</td><td>car\'s color</tr></tr>';

    var resultHtml = '<table border = "1" cellspacing="0" cellpadding="5">';
    resultHtml += header;
    for(var i = 0; i < rows.length; ++i){
        resultHtml += '<tr>';
        resultHtml += '<td>';
        resultHtml += rows[i].firstName;
        resultHtml += '</td>';

        resultHtml += '<td>';
        resultHtml += rows[i].secondName;
        resultHtml += '</td>';

        resultHtml += '<td>';
        resultHtml += rows[i].city;
        resultHtml += '</td>';

        resultHtml += '<td>';
        resultHtml += rows[i].car_color;
        resultHtml += '</td>';
        resultHtml += '</tr>'
    }
    resultHtml += '</table>';

    $('#resultTable').empty();
    $('#resultTable').append(resultHtml);
}