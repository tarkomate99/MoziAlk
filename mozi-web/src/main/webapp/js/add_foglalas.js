function setvalue(value){
    var ar=0;
    if(document.getElementById("jegy").value==="VIP"){
        ar=value*2000;
    } else if(document.getElementById("jegy").value==="Normal"){
        ar=value*1500;
    }
    document.getElementById('ar').value=ar+" Ft";
}