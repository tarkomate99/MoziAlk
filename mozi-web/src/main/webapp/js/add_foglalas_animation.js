$(document).ready(function() {
    var hely=0;
    $('table tr td').mouseover(function() {
        $(this).addClass('over');
    }).mouseout(function() {
        $(this).removeClass('over');
    }).click(function() {
        $(this).addClass('active');
        hely++;
        $('#helyek').val(hely);
        $('#helyek').click();
    });

    $(".controls a").click(function() {
        var $active = $("table tr td.active");
        if($active.length > 0) {
            var move = $.parseJSON($(this).attr('data-move'));
            if(move.length >= 2) {
                $active.each(function() {
                    var row = parseInt($(this).attr('data-row')) + move[1],
                        col = parseInt($(this).attr('data-col')) + move[0];
                    if(col >= cols) col = cols - 1;
                    if(col < 0) col = 0;
                    if(row >= rows) row = rows - 1;
                    if(row < 0) row = 0;
                    $(this).removeClass('active');
                    $('table tr').eq(row).find('td').eq(col).addClass('active');
                });
            }
        }
    });
    $('#veglegesit').click(function (){
        $('#table').addClass('disable');
        $('#lefoglalthelyek').val(lefoglalthelyek);
        // for(var key of map.keys()){
        //     console.log(key+"=>"+map.get(key)+"\n")
        // }
    });
});
const cells = document.querySelectorAll('td');
var array = Array.from('ABCDEFGHIJKLMNOPQRSTUVWXYZ');
var lefoglalthelyek="";
// var keys = [];
// var values = [];
// var map = new Map();
cells.forEach(cell => {
    cell.addEventListener('click', () =>
    lefoglalthelyek+=(array[(cell.closest('tr').rowIndex)] + cell.cellIndex)+", ");
    // var helyek = document.getElementById("helyek").value;
    // for (var i=0; i<helyek;i++){
    //     keys.push(cell.closest('tr').rowIndex);
    //     values.push(cell.cellIndex);
    // }
    // for(var i=0; i<keys.length;i++){
    //     map.set(keys[i],values[i]);
    // }
});

