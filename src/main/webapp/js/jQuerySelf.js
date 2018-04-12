+function () {
    /**
     * $.min()这种方式
     * 
     * 对$本身扩展方法
     * **/
    $.extend({
        //2个数最小值
        min: function (a, b) {
            return a < b ? a : b
        },
        //2个数最大值
        max: function (a, b) {
            return a > b ? a : b
        },
        //去除左边空格
        leftTrim: function (str) {
            return str.replace(/^\s+/, '')
        },
        //去除右边空格
        rightTrim: function (str) {
            return str.replace(/\s+$/, '')
        }
    })
    /**
     * $('className').checkAll()
     * 
     * 对jQuery元素集扩展方法
     * **/
    $.fn.extend({
        //全选
        checkAll: function () {
            this.prop('checked', true) //this是jQuery对象
        },
        //全不选
        unCheckAll: function () {
            this.prop('checked', false)
        },
        //反选
        reverseCheck: function () {
            //this是jQuery对象
            this.each(function () {
                // this是dom元素
                this.checked = !this.checked
            })
        }
    })

}()