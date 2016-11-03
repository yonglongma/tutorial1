
// 获取请求路径中的参数
function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

// json日期格式转换为正常格式：年月日-时分秒
function dateFormatYMDHM(jsonDate) {
    try {
        var date = new Date(parseInt(jsonDate.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hour = date.getHours();
        if(hour < 10){
            hour = "0" + hour;
        }
        var min = date.getMinutes();
        if(min < 10){
            min = "0" + min;
        }
        return date.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + min;
    } catch (ex) {
        return "";
    }
}
// json日期格式转换为正常格式：年月日
function dateFormatYMD(jsonDate) {
    try {
        var date = new Date(parseInt(jsonDate.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return date.getFullYear() + "-" + month + "-" + day ;
    } catch (ex) {
        return "";
    }
}

// 格式化时间
function formatDate (strTime) {
    var date = new Date();
    date.setTime(strTime);
    var now = new Date();

    var reduce = now.getTime() - date.getTime();
    if(reduce <= 600000) {
        if(reduce <= 60000) {
            return Math.ceil(reduce / 1000) + "秒前";
        } else {
            return Math.ceil(reduce / 60000) + "分钟前";
        }
    }
    var min = date.getMinutes();
    if(min < 10){
        min = "0" + min;
    }
    if(now.getYear() == date.getYear()) {
        if(now.getMonth() == date.getMonth()) {
            if(date.getDate() == now.getDate()) {
                return "今天 " + date.getHours() + ":" + min;
            } else if(date.getDate() == now.getDate() - 1) {
                return "昨天 " + date.getHours() + ":" + min;
            } else if(date.getDate() == now.getDate() - 2) {
                return "前天 " + date.getHours() + ":" + min;
            }
        }
        return (date.getMonth()+1) + "月" + date.getDate() + "日 " + date.getHours() + ":" + min;
    } else {
        return (date.getYear() + 1900) + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日 " + date.getHours() + ":" + min;
    }
}