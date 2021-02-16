$(document).ready(function () {
    var web3Provider;
    web3Provider = new Web3.providers.HttpProvider('http://127.0.0.1:8545');
    web3js = new Web3(web3Provider);//web3js就是你需要的web3实例
    $("#submit").bind("click",function () {
        $.ajax({
            url:"/jump/getHashStr",
            type:"post",
            success:function (result) {
                console.log(web3js.eth.accounts[0]);
                web3js.eth.sendTransaction({from:result,to:web3js.eth.accounts[1],value:1000},(err,res)=>{
                    if (err){
                        console.log("ERR:"+err);
                    }else{
                        console.log("RES:"+res);
                    }
                });
            }
        });
    });
});