var ip = "http://localhost:8080/";
var ends = "";
//var ip = "test/"
//var ends = ".json";

layui.use(['element', 'layer', 'laypage', 'form'], function()
    {
        var element = layui.element();
        var layer = layui.layer;

        // 左侧导航栏
        element.on('nav(leftNav)', function(elem)
            {
                var tabTitleDiv = $('.layui-tab[lay-filter="tab"]').children('.layui-tab-title');
                var id = $(this).children('a').attr('lay-id');
                var exist = tabTitleDiv.find('li[lay-id=' + id + ']');
                var obj = eval(id);

                if(exist.length <= 0)
                {
                    element.tabAdd('tab',
                    {
                        title: $(this).children('a').text(),
                        content: obj.content(),
                        id: id
                    });
                }

                element.tabChange('tab', id);
                if(exist.length <= 0)
                {
                    obj.chart();
                }
            }
        )

        element.on('tab(tab)', function(data)
            {
                $(".layui-show").addClass('fade-in-right');
            }
        )

        element.on('nav(user)', function(elem)
            {
                $(this).removeClass('layui-this');

                if($(this).text().indexOf('修改密码') != -1)
                {
                    layer.open(
                        {
                            title: "修改密码",
                            content: $("#changePass").html(),
                            area: '400px'
                        }
                    )
                }
                else
                {
                    layer.msg('退出');
                }
            }
        )
    }
);

$(
    function()
    {
        overview.charts();
    }
)

function flash()
{
    layui.use(['element', 'layer'], function()
        {
            var element = layui.element();
            var layer = layui.layer;

            var id = $('.layui-tab[lay-filter="tab"]').children('.layui-tab-title')
                .children(".layui-this").attr("lay-id");
            element.tabDelete('tab', id);
            var obj = eval(id);

            $(".layui-nav.layui-nav-tree").find('a[lay-id=' + id + ']').click();
        }
    )
}

function jia0(num)
{
    if(num < 10)
    {
        return "0" + num;
    }
    return num;
}

var overview =
{
    'charts': function()
    {
        $.ajax(
            {
                type: "get",
                url: ip + "OverView" + ends,
                dataType: 'json',
                success: function(data)
                {
                    if(data && data.status >= 1)
                    {
                        data = data.data;
                        initStaffNumChart(data.groupAndPersonNums);
                        initMinuteInfoChart(data.minuteInfo);
                        initDayInfoChart(data.weekInfo);
                        inithalfMonthInfoChart(data.halfMonth);
                    }
                }
            }
        )
    }
}

function inithalfMonthInfoChart(data)
{
    let halfMonthInfoXAxisData = [];
    let halfMonthInfoSeriesData = [];

    var time = new Date();
    time = new Date(+time - 15 * 24 * 60 * 60 * 1000);
    for(let i = 0; i< 15; i++)
    {
        halfMonthInfoXAxisData.push((time.getMonth() + 1) + "-" + time.getDate());
        time = new Date(+time + 1 * 24 * 60 * 60 * 1000);
    }

    for(let i = 0; i < data.length; i++)
    {
        let tmp = data[i];
        let times = tmp.times;
        let date = new Date(tmp.time);
        halfMonthInfoSeriesData.push(
            [(date.getMonth() + 1) + "-" + date.getDate(), times]
        );
    }

    halfMonthInfoSeriesData = [];
    for(var i = 0; i < 15; i++)
    {
        // let date = Math.ceil(Math.random() * 10) + "-" + Math.ceil(Math.random() * 10);
        // halfMonthInfoXAxisData.push(date);
        halfMonthInfoSeriesData.push(Math.ceil(20 + Math.random() * 20));
    }

    halfMonthInfoChart = echarts.init($('#halfMonthInfo').get(0));
    halfMonthInfoChart.showLoading();
    halfMonthInfoChart.setOption(
        {
            title:
            {

            },

            legend:
            {
                left: '0px',
                data: ["次数"]
            },

            tooltip:
            {

            },

            grid:
            {
                left: "5%",
                right: "5%",
                top: "10%",
            },

            xAxis:
            {
                data: halfMonthInfoXAxisData
            },

            yAxis:
            {

            },

            series:
            [
                {
                    name: "次数",
                    type: 'bar',
                    data: halfMonthInfoSeriesData
                }
            ]
        }
    )
    halfMonthInfoChart.hideLoading();

    $(window).resize(
        function()
        {
            halfMonthInfoChart.resize();
        }
    )
}

function initStaffNumChart(groupAndPersonNums)
{
    let staffNumChartLegend = [];
    let staffNumChartSeriesData = [];
    for(let i = 0; i < groupAndPersonNums.length; i++)
    {
        let tmp = groupAndPersonNums[i];
        var name = tmp.serviceGroup.name;
        staffNumChartLegend.push(name);
        staffNumChartSeriesData.push(
            {
                'name': name,
                'value': tmp.total
            }
        );
    }

    staffNumChart = echarts.init($("#staffNum").get(0));
    staffNumChart.showLoading();
    staffNumChart.setOption(
        {
            legend:
            {
                orient: "vertical",
                left: "0%",
                data: ['售前组', '售后组']
                // data: staffNumChartLegend
            },

            tooltip:
            {

            },

            grid:
            {
                left: "50%",
                right: "0%",
                top: "10%",
                width: "80%"
            },

            series:
            {
                name: '部门统计',
                type: 'pie',
                radius: '55%',
                // data: staffNumChartSeriesData
                data:
                [
                    {value:10, name:'售前组'},
                    {value:20, name:'售后组'},
                ]
            }
        }
    )
    staffNumChart.hideLoading();

    $(window).resize(
        function()
        {
            staffNumChart.resize();
        }
    )
}

function initMinuteInfoChart(data)
{
    var now = new Date();
    now = new Date(now - (1440 * 60 * 1000));
    var minuteInfoChartDatas = [];
    var minuteInfoChartDates = [];

    function addData(shift)
    {
        minuteInfoChartDate = [jia0(now.getMonth() + 1), jia0(now.getDate())].join('-') + ' ' + [jia0(now.getHours()), jia0(now.getMinutes()), jia0(now.getSeconds())].join(':');

        minuteInfoChartDates.push(minuteInfoChartDate);
        minuteInfoChartDatas.push(Math.ceil(20 + Math.random() * 20));

        if (shift)
        {
            minuteInfoChartDates.shift();
            minuteInfoChartDatas.shift();
        }

        now = new Date(+now + (1 * 10 * 1000));
    }

    for(var i = 0; i < 30; i++)
    {
        addData();
    }

    var minuteInfoChart = echarts.init($("#minuteInfo").get(0));

    function changeMinuteInfoChart()
    {
        minuteInfoChart.setOption(
            {
                title:
                {

                },

                legend:
                {
                    data: ['会话数'],
                },

                tooltip:
                {
                    axisPointer:
                    {
                        type: 'cross'
                    },
                },

                dataZoom:
                [
                    {
                        xAxisIndex: [0],
                        startValue: minuteInfoChartDates[minuteInfoChartDates.length - 10],
                    },
                    {
                        yAxisIndex: [0],
                    }
                ],

                xAxis:
                {
                    data: minuteInfoChartDates
                },

                axisPointer:
                {
                    link:
                    {
                        xAxisIndex: 'all'
                    },
                },

                yAxis:
                {

                },

                series:
                [
                    {
                        name: '会话数',
                        type: 'line',
                        data: minuteInfoChartDatas
                    }
                ]
            }
        )
    }
    changeMinuteInfoChart();
    setInterval(function()
    {
        addData(true);
        minuteInfoChart.setOption(
            {
                xAxis:
                {
                    data: minuteInfoChartDates
                },

                series:
                [
                    {
                        name:'会话数',
                        data: minuteInfoChartDatas
                    }
                ]
            }
        )
    }, 1000 * 10);

    $(window).resize(
        function()
        {
            minuteInfoChart.resize();
        }
    )
}

function initDayInfoChart(data)
{
    // dayInfoChartYAixs = ['07-12', '07-13', '07-14', '07-15', '07-16', '07-17', '07-18'];
    let dayInfoChartYAixs = [];
    let dayInfoChartXAixs = [];
    let dayInfoChartSeries = [];
    let max = 0;

    var time = new Date();
    time = new Date(+time - 7 * 24 * 60 * 60 * 1000);
    for(let i = 0; i < 7; i++)
    {
        dayInfoChartYAixs.push((time.getMonth() + 1) + '-' + time.getDate());
        time = new Date(+time + 1 * 24 * 60 * 60 * 1000);
    }

    for(let i = 0; i < 24; i++)
    {
        var time = i + ':00';
        dayInfoChartXAixs.push(time);
    }

    // for(let i = 0; i < data.length; i++)
    // {
    //     let tmp = data[i];
    //     let time = new Date(tmp.time);
    //     let times = tmp.times;
    //     max = times > max ? times : max;
    //
    //     dayInfoChartSeries.push(
    //         [time.getHours(), (time.getMonth() + 1) + '-' + time.getDate(), times]
    //     )
    // }

    for(let i = 0; i < 24; i++)
    {
        for(let j = 0; j < 7; j++)
        {
            var times = Math.ceil(Math.random() * 40);
            max = times > max ? times : max;
            dayInfoChartSeries.push([i, j, times]);
        }
    }

    dayInfoChart = echarts.init($("#dayInfo").get(0));
    dayInfoChart.setOption(
        {
            grid:
            {
                top: '0%',
                bottom: '20%',
                left: '5%',
                right: '3%',
            },

            xAxis:
            {
                type: 'category',
                data: dayInfoChartXAixs,
                splitArea:
                {
                    show: true
                }
            },

            yAxis:
            {
                type: 'category',
                data: dayInfoChartYAixs,
                splitArea:
                {
                    show: true
                }
            },

            visualMap:
            {
                min: 0,
                max: max,
                calculable: true,
                orient: 'horizontal',
                left: 'center',
            },

            tooltip:
            {
                formatter: function(params)
                {
                    var res = `
                    <span>${params.seriesName}</span>
                    <br>
                    <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:${params.color}"></span>
                    <span>${params.data[1]}&nbsp;${params.name}</span>&nbsp;:&nbsp;<span>${params.data[2]}</span>
                    `;
                    return res;
                },
                // position: 'left',
            },

            series:
            [
                {
                    name: '会话数',
                    type: 'heatmap',
                    data: dayInfoChartSeries,
                    label:
                    {
                        normal:
                        {
                            show: true
                        }
                    },
                }
            ]
        }
    )

    $(window).resize(
        function()
        {
            dayInfoChart.resize();
        }
    )
}

var customerServiceGroup = [];
var customerServiceDetele = [];
var customerService =
{
    'content': function()
    {
        var content = $('#customerServiceContent').html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        initCustomerServiceGroup();
        setTimeout("addCustomerServiceTr(['allCustom'])", 100);
    }
}

function initCustomerServiceGroup()
{
    $.ajax(
        {
            url: ip + 'customerServiceManagement' + ends,
            dataType: 'json',
            type: "get",
            success: function(data)
            {
                if(data && data.status >= 1)
                {
                    data = data.data;
                    for(let i = 0; i < data.length; i++)
                    {
                        let tmp = data[i].serviceGroup;
                        customerServiceGroup[Number(tmp.groupId)] = tmp.name;
                    }
                }
                else
                {
                    console.log(data.message);
                }
            }
        }
    )
}

function addCustomerServiceTr([searType, parameter])
{
    console.log(searType + ", " + parameter);
    $.ajax(
        {
            url: ip + searType + ends,
            dataType: 'json',
            data: parameter,
            type: "get",
            success: function(data)
            {
                if(data && data.status >= 1)
                {
                    $("#customerService").html(" ");
                    data = data.data;
                    for(let i = 0; i < data.length; i++)
                    {
                        let tmp = data[i];
                        $("#customerService").append(`<tr>
                            <td><input type="checkbox" name="customerService" lay-skin="primary" lay-filter="customerService" serviceId="${tmp.serviceId}"></td>
                            <td>${customerServiceGroup[tmp.groupId]}</td>
                            <td>${tmp.name}</td>
                            <td>${tmp.nickname}</td>
                            <td>${tmp.employeeId}</td>
                            <td>${tmp.password}</td>
                            <td>${tmp.autoMessage}</td>
                            <td>
                                <button type="button" class="layui-btn layui-btn-small layui-btn-primary editCustomerService" groupId="${tmp.groupId}" serviceId="${tmp.serviceId}" name="">编辑</button>
                                <button type="button" class="layui-btn layui-btn-small layui-btn-normal customerServiceChatLog" name="" serviceId="${tmp.serviceId}" serviceName="${tmp.name}" id="">聊天记录</button>
                            </td>
                        </tr>`)
                    }

                    layui.use(['form'], function()
                        {
                            var form = layui.form();

                            //全选
                            form.on('checkbox(allChooseCustomerService)', function(data)
                                {
                                    customerServiceDetele = [];
                                    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
                                    child.each(function(index, item)
                                        {
                                            item.checked = data.elem.checked;
                                            if(data.elem.checked)
                                            {
                                                if(index != 0)
                                                {
                                                    customerServiceDetele.push(
                                                        {
                                                            "id": $(item).attr("serviceid"),
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    );

                                    form.render('checkbox');
                                }
                            );

                            form.on('checkbox(customerService)', function(data)
                                {
                                    var serviceId = $(data.elem).attr("serviceid");

                                    console.log(data.elem.checked);
                                    if(data.elem.checked)
                                    {
                                        customerServiceDetele.push(
                                            {
                                                "id": serviceId,
                                            }
                                        )
                                    }
                                    else
                                    {
                                        for(let  i = 0; i < customerServiceDetele.length; i++)
                                        {
                                            if(customerServiceDetele[i].id == serviceId)
                                            {
                                                customerServiceDetele.splice(i, 1);
                                                break;
                                            }
                                        }
                                    }

                                    form.render('checkbox');
                                }
                            );

                            form.render('checkbox');
                        }
                    );
                }
            }
        }
    )
}

$(document).on("click", "#addCustomerServiceBtn", function()
    {
        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();
                var groupId;

                layer.open(
                    {
                        title: "添加人员",
                        content: $("#addCustomerServiceContent").html(),
                        area: ['800px', '600px'],
                        btn1: function(index)
                        {
                            var addCustomerServiceForm = $("#addCustomerServiceForm").get(0);
                            var name = addCustomerServiceForm.name.value;
                            var nickname = addCustomerServiceForm.nickname.value;
                            var employeeId = addCustomerServiceForm.employeeId.value;
                            var autoMessage = addCustomerServiceForm.autoMessage.value;
                            var personList = [];
                            personList.push(
                                {
                                    "name": name,
                                    "groupId": groupId,
                                    "nickname": nickname,
                                    "employeeId": employeeId,
                                    "autoMessage": autoMessage
                                }
                            )


                            $.ajax(
                                {
                                    url: ip + "addServerPerson" + ends,
                                    dataType: 'json',
                                    data: "personList=" + personList,
                                    type: 'post',
                                    success: function(data)
                                    {
                                        layer.msg(data.message);
                                    }
                                }
                            )

                            layer.close(index);
                            addCustomerServiceTr(['allCustom']);
                        }
                    }
                )

                for(let i = 0; i < customerServiceGroup.length; i++)
                {
                    if(customerServiceGroup[i] != undefined)
                    {
                        $("#chooseCustomerServiceGroup").append("<option value=" + i + ">" + customerServiceGroup[i] + "</option>");
                    }
                }

                form.render();

                form.on('select(chooseCustomerServiceGroup)', function(data)
                    {
                        groupId = data.value;
                    }
                );
            }
        );
    }
)

$(document).on("click", "#deleteCustomerServiceBtn", function()
    {
        layui.use(['layer'], function()
            {
                var layer = layui.layer;

                layer.confirm('确认删除？',
                    {
                        icon: 0
                    },
                    function()
                    {
                        $.ajax(
                            {
                                type: 'post',
                                url: ip + "deteleServerPerson" + ends,
                                dataType: 'json',
                                data: "deteleGroup=" + customerServiceDetele,
                                success: function(data)
                                {
                                    layer.msg(data.message);
                                }
                            }
                        )

                        customerServiceDetele = [];
                        addCustomerServiceTr(["allCustom"]);
                    },
                    function()
                    {
                        // layer.msg("no");
                    }
                )
            }
        )
    }
)

$(document).on("click", "#filterCustomerServiceBtn", function()
    {
        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();
                var customerServiceGroupValue = "allCustom";

                layer.open(
                    {
                        title: "筛选",
                        content: $("#filterCustomerServiceContent").html(),
                        area: ['600px', '450px'],
                        btn1: function(index, layero)
                        {
                            if(customerServiceGroupValue == "allCustom")
                            {
                                addCustomerServiceTr(["allCustom"]);
                            }
                            else
                            {
                                addCustomerServiceTr(("searchGroupPerson?groupId=" + customerServiceGroupValue).split("?"));
                            }
                            layer.close(index);
                        }
                    }
                )

                $("#filterCustomerServiceGroup").append("<option value='allCustom'>   </option>").checked;
                for(let i = 0; i < customerServiceGroup.length; i++)
                {
                    if(customerServiceGroup[i] != undefined)
                    {
                        $("#filterCustomerServiceGroup").append("<option value=" + i + ">" + customerServiceGroup[i] + "</option>");
                    }
                }

                form.render();

                form.on('select(filterCustomerServiceGroup)', function(data)
                    {
                        customerServiceGroupValue = data.value;
                    }
                );
            }
        )
    }
)

$(document).on("click", "#searchCustomerServiceBtn", function()
    {
        var searchName = $(this).prev().children("input").val();
        console.log(searchName);
        if(searchName != "")
        {
            addCustomerServiceTr(["searchPerson", "name=" + searchName]);
        }
        else
        {
            addCustomerServiceTr(["allCustom"]);
        }
    }
)

$(document).on("click", ".editCustomerService", function()
    {
        var parentTr = $($(this).parents("tr").get(0));
        var serviceIdOld = $(this).attr("serviceId");
        var groupNameOld = $(parentTr.children().get(1)).text();
        var nameOld = $(parentTr.children().get(2)).text();
        var nicknameOld = $(parentTr.children().get(3)).text();
        var employeeIdOld = $(parentTr.children().get(4)).text();
        var autoMessageOld = $(parentTr.children().get(6)).text();
        var groupId = $(this).attr("groupId");

        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: "编辑人员",
                        content: $("#addCustomerServiceContent").html(),
                        area: ['800px', '600px'],
                        btn1: function(index)
                        {
                            var addCustomerServiceForm = $("#addCustomerServiceForm").get(0);
                            var name = addCustomerServiceForm.name.value;
                            var nickname = addCustomerServiceForm.nickname.value;
                            var employeeId = addCustomerServiceForm.employeeId.value;
                            var autoMessage = addCustomerServiceForm.autoMessage.value;
                            var personList = [];
                            personList.push(
                                {
                                    "serviceId": serviceIdOld,
                                    "name": name,
                                    "groupId": groupId,
                                    "nickname": nickname,
                                    "employeeId": employeeId,
                                    "autoMessage": autoMessage,
                                }
                            );


                            $.ajax(
                                {
                                    url: ip + "updateService" + ends,
                                    dataType: 'json',
                                    data: "personList=" + JSON.stringify(personList),
                                    type: 'get',
                                    success: function(data)
                                    {
                                        layer.msg(data.message);
                                        flash();
                                    }
                                }
                            )

                            layer.close(index);
                            addCustomerServiceTr(['allCustom']);
                        }
                    }
                )

                var formOld = $("#addCustomerServiceForm").get(0);
                formOld.name.value = nameOld;
                formOld.nickname.value = nicknameOld;
                formOld.employeeId.value = employeeIdOld;
                formOld.autoMessage.value = autoMessageOld;

                for(let i = 0; i < customerServiceGroup.length; i++)
                {
                    if(customerServiceGroup[i] != undefined)
                    {
                        $("#chooseCustomerServiceGroup").append("<option value=" + i + ">" + customerServiceGroup[i] + "</option>");
                    }
                }
                $("#chooseCustomerServiceGroup option:contains(" + groupNameOld + ")").attr("selected", true);

                form.render();

                form.on('select(chooseCustomerServiceGroup)', function(data)
                    {
                        groupId = data.value;
                    }
                );
            }
        );
    }
)

$(document).on("click", ".customerServiceChatLog", function()
    {
        var serviceName = $(this).attr('serviceName');
        var serviceId = $(this).attr('serviceId');
        layui.use(['element'], function()
            {
                var element = layui.element();

                var tabTitleDiv = $('.layui-tab[lay-filter="tab"]').children('.layui-tab-title');
                var exist = tabTitleDiv.find('li[lay-id=service-' + serviceId + ']');

                if(exist.length <= 0)
                {
                    element.tabAdd('tab',
                        {
                            title: '客服' + serviceName + '的聊天记录',
                            content: $('#customerServiceChatLogContent').html(),
                            id: 'service-' + serviceId,
                        }
                    );
                }

                element.tabChange('tab', 'service-' + serviceId);

                $.ajax(
                    {
                        url: ip + "clientChatList" + ends,
                        type: 'post',
                        data: "serviceId=" + serviceId,
                        dataType: 'json',
                        success: function(data)
                        {
                            if(data && data.status >= 1)
                            {
                                data = data.data;
                                var $clientList = $("#clietnList");
                                $clientList.html(" ");
                                for(let i = 0; i < data.length; i++)
                                {
                                    var tmp = data[i];
                                    if(tmp.name == null)
                                    {
                                        tmp.name = "匿名用户";
                                    }
                                    $clientList.append(`
                                        <div class="chat-list-item" serviceId="${serviceId}" clientId="${tmp.clientId}">
                                            <img class="chat-list-item-img" src="img/tx.png" alt="">
                                            <div class="chat-list-item-name">
                                                <span>${tmp.name}</span>
                                            </div>
                                        </div>`);
                                }
                            }
                        }
                    }
                )
            }
        )
    }
)

$(document).on("click", ".chat-list-item", function()
    {
        $(this).addClass('chat-list-item-on').siblings().removeClass('chat-list-item-on');
        var clientId = $(this).attr("clientId");
        var serviceId = $(this).attr("serviceId");

        $.ajax(
            {
                url: ip + "clientAndServerChatLog" + ends,
                type: 'get',
                data: "serviceId=" + serviceId + "&clientId=" + clientId,
                dataType: 'json',
                success: function(data)
                {
                    if(data && data.status >= 1)
                    {
                        data = data.data;
                        var $customerServiceChatLog = $("#customerServiceChatLog");
                        $customerServiceChatLog.html("");
                        for(let i = 0; i < data.length; i++)
                        {
                            var tmp = data[i];
                            var sendTime = new Date(tmp.time);
                            $customerServiceChatLog.append(`
                                <div class="chat-history-item">
                                    <div class="chat-history-item-title">
                                        <span class="chat-history-item-name">${tmp.senderName}&nbsp;&nbsp;&nbsp;</span>
                                        <span class="chat-history-item-time">${[sendTime.getFullYear(), jia0(sendTime.getMonth() + 1), jia0(sendTime.getDate())].join("-") + " " + [jia0(sendTime.getHours()), jia0(sendTime.getMinutes()), jia0(sendTime.getSeconds())].join(":")}</span>
                                    </div>
                                    <div class="chat-history-item-content">
                                        <span>${tmp.content}</span>
                                    </div>
                                </div>
                            </div>`);
                        }

                        $customerServiceChatLog.scrollTop($customerServiceChatLog.get(0).scrollHeight);
                    }
                }
            }
        )
    }
)

function initTimeInput(startTimeInput, endTimeInput)
{
    layui.use('laydate', function()
        {
            var laydate = layui.laydate;

            var startTime = laydate.now(-1, 'YYYY-MM-DD');
            var endsTime = startTime;
            var $startTimeInput = $(startTimeInput);
            var $endTimeInput = $(endTimeInput);

            $startTimeInput.val(laydate.now(-1, 'YYYY-MM-DD'));
            $startTimeInput.click(function()
                {
                    laydate(
                        {
                            elem: this,
                            max: endsTime,
                            isTime: true,
                            isToday: false,
                            choose: function(dates)
                            {
                                startTime = dates;
                            }
                        }
                    );
                }
            );

            $endTimeInput.val(laydate.now(-1, 'YYYY-MM-DD'));
            $endTimeInput.click(function()
                {
                    laydate(
                        {
                            elem: this,
                            min: startTime,
                            max: laydate.now(-1),
                            isTime: true,
                            isToday: false,
                            choose: function(dates)
                            {
                                endsTime = dates;
                            }
                        }
                    );
                }
            );
        }
    )
}

function validateTime(startTime, endTime)
{
    return endTime >= startTime ? true : false;
}

var wholePerformance =
{
    'content': function()
    {
        var content = $("#wholePerformanceContent").html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        initTimeInput("#wholePerformanceStartTime", "#wholePerformanceEndTime");
        initWholePerformance(new Date(+(new Date()) - 1 * 24 * 60 * 60 * 1000).getTime());
    }
}

function initWholePerformance(startTime, endsTime)
{
    endsTime = endsTime == undefined ? startTime : endsTime;

    if(validateTime(startTime, endsTime))
    {
        $.ajax(
            {
                url: ip + "selectDuringTimeInfo" + ends,
                type: 'get',
                data:
                {
                    'startTime': startTime,
                    'stopTime': endsTime
                },
                dataType: 'json',
                success: function(data)
                {
                    if(data && data.status >= 1)
                    {
                        data = data.data;
                        initCustomerSourceChart(data.accessAndNumDurings);
                        initScoreDistributionChart(data.scoreAndNums);
                    }
                }
            }
        )
    }
    else
    {
        layui.use(['layer'], function()
            {
                var layer = layui.layer;

                layer.alert('起始时间应 <= 终止时间', function(index)
                    {
                        layer.close(index);
                    }
                );
            }
        )
    }
}

function initCustomerSourceChart(data)
{
    var customerSourceChartLegend = [];
    var customerSourceChartSeriesData = [];

    customerSourceChartLegend = ['微信', 'QQ', '微博', '网页'];
    customerSourceChartSeriesData = [
        {
            'name': '微信',
            'value': 12,
        },
        {
            'name': 'QQ',
            'value': 58,
        },
        {
            'name': '微博',
            'value': 156,
        },
        {
            'name': '网页',
            'value': 88,
        }
    ]

    // customerSourceChartLegend = [];
    // customerSourceChartSeriesData = [];
    // for(let i = 0; i < data.length; i++)
    // {
    //     let tmp = data[i];
    //     var name = tmp.name;
    //     customerSourceChartLegend.push(name);
    //     customerSourceChartSeriesData.push(
    //         {
    //             'name': name,
    //             'value': tmp.num
    //         }
    //     );
    // }

    var customerSourceChart = echarts.init($("#customerSource").get(0));
    customerSourceChart.showLoading();
    customerSourceChart.setOption(
        {
            legend:
            {
                orient: "vertical",
                left: "0%",
                data: customerSourceChartLegend
            },

            tooltip:
            {

            },

            grid:
            {
                left: "50%",
                right: "0%",
                top: "10%",
                width: "80%"
            },

            series:
            {
                name: '部门统计',
                type: 'pie',
                radius: '55%',
                data: customerSourceChartSeriesData
            }
        }
    )
    customerSourceChart.hideLoading();

    $(window).resize(
        function()
        {
            customerSourceChart.resize();
        }
    )
}

function initScoreDistributionChart(data)
{
    var scoreDistributionChartLegend = [];
    var scoreDistributionChartSeriesData = [];

    scoreDistributionChartLegend = ['5分', '4分', '3分', '2分', '1分'];
    scoreDistributionChartSeriesData = [
        {
            'name': '5分',
            'value': 126,
        },
        {
            'name': '4分',
            'value': 60,
        },
        {
            'name': '3分',
            'value': 50,
        },
        {
            'name': '2分',
            'value': 28,
        },
        {
            'name': '1分',
            'value': 15,
        }
    ]

    // scoreDistributionChartLegend = [];
    // scoreDistributionChartSeriesData = [];
    // for(let i = 0; i < data.length; i++)
    // {
    //     let tmp = data[i];
    //     var countNum = tmp.countNum;
    //     var score = tmp.score + "分";
    //     scoreDistributionChartLegend.push(score);
    //     scoreDistributionChartSeriesData.push(
    //         {
    //             'name': score,
    //             'value': countNum
    //         }
    //     );
    // }

    var scoreDistributionChart = echarts.init($("#scoreDistribution").get(0));
    scoreDistributionChart.showLoading();
    scoreDistributionChart.setOption(
        {
            legend:
            {
                orient: "vertical",
                left: "0%",
                data: scoreDistributionChartLegend
            },

            tooltip:
            {

            },

            grid:
            {
                left: "50%",
                right: "0%",
                top: "10%",
                width: "80%"
            },

            series:
            {
                name: '部门统计',
                type: 'pie',
                radius: '55%',
                data: scoreDistributionChartSeriesData
            }
        }
    )
    scoreDistributionChart.hideLoading();

    $(window).resize(
        function()
        {
            scoreDistributionChart.resize();
        }
    )
}

$(document).on("click", "#chooseWholePerformanceTime", function()
    {
        var startTime = new Date($("#wholePerformanceStartTime").val()).getTime();
        var endsTime = new Date($("#wholePerformanceEndTime").val()).getTime();

        initWholePerformance(startTime, endsTime);
    }
)

var personalPerformance =
{
    'content': function()
    {
        var content = $("#personalPerformanceContent").html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        initTimeInput('#personalPerformanceStartTime', '#personalPerformanceEndTime');
        addPersonalPerformanceTr(new Date(+(new Date()) - 1 * 24 * 60 * 60 * 1000).getTime());
    }
}

function addPersonalPerformanceTr(startTime, endsTime)
{
    endsTime = endsTime == undefined ? startTime : endsTime;

    if(validateTime(startTime, endsTime))
    {
        $.ajax(
            {
                url: ip + "rankInfo" + ends,
                type: 'get',
                data:
                {
                    'startTime': startTime,
                    'stopTime': new Date(+endsTime + 24 * 60 * 60 * 1000).getTime()
                },
                dataType: 'json',
                success: function(data)
                {
                    if(data && data.status >= 1)
                    {
                        data = data.data;
                        var $personalPerformance = $("#personalPerformance");
                        $personalPerformance.html("");
                        for(let i = 0; i < data.length; i++)
                        {
                            var tmp = data[i];
                            var conNumAndRanks = tmp.conNumAndRanks[0];
                            if(conNumAndRanks == null)
                            {
                                conNumAndRanks = {};
                                conNumAndRanks.num = "暂无";
                                conNumAndRanks.numrank = "暂无";
                            }
                            var scoreAndRanks = tmp.scoreAndRanks[0];
                            if(scoreAndRanks == null)
                            {
                                scoreAndRanks = {};
                                scoreAndRanks.grades = "暂无";
                                scoreAndRanks.gradesrank = "暂无";
                            }
                            var timeAndRanks = tmp.timeAndRanks[0];
                            if(timeAndRanks == null)
                            {
                                timeAndRanks = {};
                                timeAndRanks.timerank = "暂无";
                                var tmpTime = new Date();
                                tmpTime.setHours(0);
                                tmpTime.setMinutes(0);
                                tmpTime.setSeconds(0);
                                tmpTime.setMilliseconds(0);
                                timeAndRanks.tottime = tmpTime.getTime();
                            }

                            var time = new Date(timeAndRanks.tottime);
                            $personalPerformance.append(`
                                <tr>
                                    <td>${tmp.customerService.name}</td>
                                    <td>${tmp.serviceGroup.name}</td>
                                    <td>${conNumAndRanks.num}&nbsp;&nbsp;/&nbsp;&nbsp;${conNumAndRanks.numrank}</td>
                                    <td>${[jia0(time.getHours()), jia0(time.getMinutes()), jia0(time.getSeconds())].join(":")}&nbsp;&nbsp;/&nbsp;&nbsp;${timeAndRanks.timerank}</td>
                                    <td>${scoreAndRanks.grades}&nbsp;&nbsp;/&nbsp;&nbsp;${scoreAndRanks.gradesrank}</td>
                                </tr>`);
                        }
                    }
                }
            }
        )
    }
    else
    {
        layui.use(['layer'], function()
            {
                var layer = layui.layer;

                layer.alert('起始时间应 <= 终止时间', function(index)
                    {
                        layer.close(index);
                    }
                );
            }
        )
    }
}

$(document).on("click", "#choosePersonalPerformanceTime", function()
    {
        var startTime = new Date($("#personalPerformanceStartTime").val()).getTime();
        var endsTime = new Date($("#personalPerformanceEndTime").val()).getTime();

        addPersonalPerformanceTr(startTime, endsTime);
    }
)

var newKeyWords = [];
var levelGroup = ['全平台', '公司', '客服'];
var level = 1;
var knowledgeBase =
{
    'content': function()
    {
        var content = $("#knowledgeBaseContent").html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        addKnowledgeBaseDiv(['knowledgeSearch', 'keyword=']);
        // setTimeout('waterfall()', 100);
    }
}

function waterfall()
{
    var $container = $('#masonry');
    $container.imagesLoaded(function()
        {
            $container.masonry(
                {
                    itemSelector: '.col-lg-3',
                    gutter: 0,
                    isAnimated: true,
                }
            );
        }
    );
}

function addKnowledgeBaseDiv([searType, parameter])
{
    console.log(searType + ", " + parameter);
    layui.use(['layer', 'form'], function()
        {
            var layer = layui.layer;

            $.ajax(
                {
                    url: ip + searType + ends,
                    type: 'get',
                    data: parameter,
                    dataType: 'json',
                    success: function(data)
                    {
                        console.log(data);
                        if(data && data.status >= 1)
                        {
                            data = data.data;
                            $("#masonry").html("");
                            for(let i = 0; i < data.length; i++)
                            {
                                var tmp = data[i];
                                var keyWordsArray = tmp.keywords;
                                var knowledgeObj = tmp.knowledge;
                                var ques = knowledgeObj.question;

                                var tagsDiv = "";
                                for(let i = 0; i < keyWordsArray.length; i++)
                                {
                                    if(keyWordsArray[i].value != ques)
                                    {
                                        tagsDiv += addKeyWordDiv(keyWordsArray[i].value);
                                    }
                                }
                                appendKeyWord();

                                $("#masonry").append(`
                                    <div class="col-lg-3">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <div class="close-local">
                                                    <i class="fa fa-pencil editKnowledge" knowledgeId="${knowledgeObj.knowledgeId}" title="编辑" ></i>
                                                    <i class="layui-icon close-btn deteleKnowledge" knowledgeId="${knowledgeObj.knowledgeId}" title="删除">&#x1006;</i>
                                                </div>
                                                <div class="level" level="${knowledgeObj.level}">
                                                    类别： ${levelGroup[knowledgeObj.level]}
                                                </div>
                                                <div class="ques" ques="${ques}">
                                                    问题： ${ques}
                                                </div>
                                                <div class="ans" ans="${knowledgeObj.answer}">
                                                    答案： ${knowledgeObj.answer}
                                                </div>
                                                <div class="tags">
                                                    ${tagsDiv}
                                                </div>
                                            </div>
                                        </div>
                                    </div>`);


                            }

                        }
                        else
                        {
                            layer.msg(data.message);
                        }
                    }
                }
            )
        }
    )
}

function addKeyWordDiv(value, flag)
{
    //  ${!flag?'keywordId="${tmp.keywordId}"':''}
    var div = `
    <div class="tag">
        ${value}
        ${flag?'<i class="layui-icon tag-close-local">&#x1006;</i>':''}
    </div>`;

    return div;
}

function appendKeyWord()
{
    var tagsDiv = "";
    for(let i = 0; i < newKeyWords.length; i++)
    {
        tagsDiv += addKeyWordDiv(newKeyWords[i], true);
    }
    $("#keyWords").append(tagsDiv);
}

$(document).on("click", ".editKnowledge", function()
    {
        var parentDiv = $(this).parents(".panel-body");
        var quesOld = parentDiv.find("div[ques]").attr("ques");
        var ansOld = parentDiv.find("div[ans]").attr("ans");
        var levelOld = parentDiv.find("div[level]").attr("level");
        var knowledgeId = $(this).attr("knowledgeId");
        newKeyWords = [];
        parentDiv.find(".tag").each(function()
            {
                newKeyWords.push($(this).text().trim());
            }
        );
        console.log(newKeyWords);

        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: "编辑知识",
                        content: $("#addKnowledgeBaseContent").html(),
                        area: ['600px', '750px'],
                        btn1: function()
                        {
                            var addKnowledgeBaseForm = $("#addKnowledgeBaseForm").get(0);
                            var ques = addKnowledgeBaseForm.ques.value;
                            var ans = addKnowledgeBaseForm.ans.value;

                            $.ajax(
                                {
                                    url: ip + "updateKnowledge" + ends,
                                    type: 'get',
                                    data: JSON.stringify(
                                            {
                                                "knowledgeId": knowledgeId,
                                                "question": ques,
                                                "ans": ans,
                                                "tag": newKeyWords.join(" "),
                                                "level": levelOld,
                                            }
                                        ),
                                    dataType: 'json',
                                    success: function(data)
                                    {
                                        layer.msg(data.message);
                                        flash();
                                    }
                                }
                            )

                            newKeyWords = [];
                        },
                        cancle: function()
                        {
                            newKeyWords = [];
                        }
                    }
                );

                var addKnowledgeBaseFormOld = $("#addKnowledgeBaseForm").get(0);
                addKnowledgeBaseFormOld.ques.value = quesOld;
                addKnowledgeBaseFormOld.ans.value = ansOld;
                $(addKnowledgeBaseFormOld).find("input[type='radio'][value=" + level + "]").attr("checked", true);

                appendKeyWord();

                form.render();

                form.on('radio(level)', function(data)
                    {
                        console.log(data.value); //被点击的radio的value值
                        levelOld = data.value;
                    }
                )
            }
        );
    }
)

$(document).on("click", ".deteleKnowledge", function()
    {
        var knowledgeId = $(this).attr("knowledgeId");
        layui.use(['element', 'layer'], function()
            {
                var element = layui.element();
                var layer = layui.layer;

                layer.confirm("确认删除？",
                    {
                        icon: 0
                    },
                    function(index)
                    {
                        $.ajax(
                            {
                                url: ip + "deleteKnowledge" + ends,
                                type: 'get',
                                data: "knowledgeId=" + Number(knowledgeId),
                                dataType: 'json',
                                success: function (data)
                                {
                                    layer.msg(data.message);
                                    flash();
                                }
                            }
                        )

                        layer.close(index);
                    },
                    function()
                    {

                    }
                )
            }
        )
    }
)

// $(document).on("click", "#filterKnowledgeBaseBtn", function()
//     {
//         layui.use(['layer', 'form'], function()
//             {
//                 var layer = layui.layer;
//                 var form = layui.form();
//                 var customerServiceGroupValue = "allCustom";
//
//                 layer.open(
//                     {
//                         title: "筛选",
//                         content: $("#filterCustomerServiceContent").html(),
//                         area: ['600px', '450px'],
//                         btn1: function(index, layero)
//                         {
//                             if(customerServiceGroupValue == "allCustom")
//                             {
//                                 addCustomerServiceTr(["allCustom"]);
//                             }
//                             else
//                             {
//                                 addCustomerServiceTr(("searchGroupPerson?groupId=" + customerServiceGroupValue).split("?"));
//                             }
//                             layer.close(index);
//                         }
//                     }
//                 )
//
//                 $("#filterCustomerServiceGroup").append("<option value='allCustom'></option>").checked;
//                 for(let i = 0; i < customerServiceGroup.length; i++)
//                 {
//                     if(customerServiceGroup[i] != undefined)
//                     {
//                         $("#filterCustomerServiceGroup").append("<option value=" + i + ">" + customerServiceGroup[i] + "</option>");
//                     }
//                 }
//
//                 form.render();
//
//                 form.on('select(filterCustomerServiceGroup)', function(data)
//                     {
//                         customerServiceGroupValue = data.value;
//                     }
//                 );
//             }
//         )
//     }
// )

$(document).on("click", "#addKnowledgeBaseBtn", function()
    {
        newKeyWords = [];
        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: "添加知识",
                        content: $("#addKnowledgeBaseContent").html(),
                        area: ['600px', '750px'],
                        btn1: function()
                        {
                            var addKnowledgeBaseForm = $("#addKnowledgeBaseForm").get(0);
                            var ques = addKnowledgeBaseForm.ques.value;
                            var ans = addKnowledgeBaseForm.ans.value;
                            console.log({
                                'question': ques,
                                'ans': ans,
                                'tag': newKeyWords.join(" "),
                                'level': level
                            });

                            $.ajax(
                                {
                                    url: ip + "addKnowledge" + ends,
                                    type: 'post',
                                    // data: "question=" + ques + "&ans=" + ans + "&tag="+ newKeyWords.join(" ") + "&level=" + level,
                                    data:
                                    {
                                        'question': ques,
                                        'ans': ans,
                                        'tag': newKeyWords.join(" "),
                                        'level': level
                                    },
                                    dataType: 'json',
                                    success: function(data)
                                    {
                                        layer.msg(data.message);
                                        flash();
                                    }
                                }
                            )


                            newKeyWords = [];
                        },
                        cancle: function()
                        {
                            newKeyWords = [];
                        }
                    }
                )

                form.render();

                form.on('radio(filter)', function(data)
                    {
                        console.log(data.value); //被点击的radio的value值
                        level = data.value;
                    }
                )
            }
        );
    }
)

$(document).on("click", "#searchKnowledgeBaseBtn", function()
    {
        var keyword = $(this).prev().children("input").val();
        addKnowledgeBaseDiv(['knowledgeSearch', 'keyword=' + keyword]);
    }
)

$(document).on("click", "#addTag", function()
    {
        console.log(newKeyWords);
        var keyWordsDiv = $("#keyWords");
        var newKeyWord = $("#keyWordInput").val();
        if(newKeyWord != "" && keyWordsDiv.find(".tag:contains(" + newKeyWord + ")").get(0) == undefined)
        {
            keyWordsDiv.append(`
                <div class="tag">
                    ${newKeyWord}
                    <i class="layui-icon tag-close-local">&#x1006;</i>
                </div>`);
            newKeyWords.push(newKeyWord);
            $("#keyWordInput").val("");
        }
    }
)

$(document).on("click", ".tag .tag-close-local", function()
    {
        var thisTag = $(this).parents(".tag");
        for(let i = 0; i < newKeyWords.length; i++)
        {
            if(thisTag.text().includes(newKeyWords[i]))
            {
                newKeyWords.splice(i, 1);
            }
        }
        thisTag.remove();
    }
)

var commonLanguageDetele = [];
var commonLanguage =
{
    'content': function()
    {
        var content = $("#commonLanguageContent").html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        addCommonLanguageTr();
    }
}

function addCommonLanguageTr()
{
    $.ajax(
        {
            // url: ip + searType + ends,
            url: ip + "commonLanguage" + ends,
            dataType: 'json',
            // data: parameter,
            type: "get",
            success: function(data)
            {
                if(data && data.status >= 1)
                {
                    $("#commonLanguage").html("");
                    data = data.data;
                    for(let i = 0; i < data.length; i++)
                    {
                        let tmp = data[i];
                        $("#commonLanguage").append(`<tr>
                            <td><input type="checkbox" name="" lay-skin="primary" lay-filter="commonLanguage" commonLanguageId="${tmp.commonLanguageId}"></td>
                            <td>${tmp.content}</td>
                            <td>${tmp.frequency}</td>
                        </tr>`);
                    }
                }

                layui.use('form', function()
                    {
                        var form = layui.form();

                        //全选
                        form.on('checkbox(allChooseCommonLanguage)', function(data)
                            {
                                commonLanguageDetele = [];
                                var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
                                child.each(function(index, item)
                                    {
                                        item.checked = data.elem.checked;
                                        if(data.elem.checked)
                                        {
                                            if(index != 0)
                                            {
                                                commonLanguageDetele.push(
                                                    {
                                                        "id": $(item).attr("commonLanguageId"),
                                                    }
                                                )
                                            }
                                        }
                                    }
                                );
                                form.render('checkbox');
                            }
                        );

                        form.on('checkbox(commonLanguage)', function(data)
                            {
                                var commonLanguageId = $(data.elem).attr("commonLanguageId");

                                if(data.elem.checked)
                                {
                                    commonLanguageDetele.push(
                                        {
                                            "id": commonLanguageId,
                                        }
                                    )
                                }
                                else
                                {
                                    for(let  i = 0; i < commonLanguageDetele.length; i++)
                                    {
                                        if(commonLanguageDetele[i].id == commonLanguageId)
                                        {
                                            commonLanguageDetele.splice(i, 1);
                                            break;
                                        }
                                    }
                                }
                                form.render('checkbox');
                            }
                        );

                        form.render('checkbox');
                    }
                );
            }
        }
    )


}

$(document).on("click", "#deleteCommonLanguageBtn", function()
    {
        layui.use(['layer'], function()
            {
                var layer = layui.layer;

                layer.confirm('确认删除？',
                    {
                        icon: 0
                    },
                    function()
                    {
                        $.ajax(
                            {
                                type: 'get',
                                url: ip + "deleteCommonLanguage" + ends,
                                dataType: 'json',
                                data: "deleteId=" + commonLanguageDetele,
                                success: function(data)
                                {
                                    layer.msg(data.message);
                                    flash();
                                }
                            }
                        )

                        commonLanguageDetele = [];
                        addCommonLanguageTr();
                    },
                    function()
                    {
                        // layer.msg("no");
                    }
                )
            }
        )
    }
)

$(document).on("click", "#addCommonLanguageBtn", function()
    {
        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: "添加常用语",
                        content: $("#addCommonLanguageContent").html(),
                        area: ['600px', '300px'],
                        btn1: function()
                        {
                            var addCommonLanguageForm = $("#addCommonLanguageForm").get(0);
                            var content = addCommonLanguageForm.content.value;

                            $.ajax(
                                {
                                    url: ip + "addCommonLanguage" + ends,
                                    type: 'get',
                                    data: "content=" + content,
                                    dataType: 'json',
                                    success: function(data)
                                    {
                                        layer.msg(data.message);
                                        flash();
                                    }
                                }
                            )

                            addCommonLanguageTr();
                        },
                        cancle: function()
                        {

                        }
                    }
                )

                form.render();

                form.on('radio(filter)', function(data)
                    {
                        level = data.value;
                    }
                )
            }
        );
    }
)

var client =
{
    'content': function()
    {
        var content = $("#clientContent").html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        addClientTr("searchName=");
    }
}

function addClientTr(parameter)
{
    console.log("clientSearchByName, " + parameter);
    $.ajax(
        {
            url: ip + "clientSearchByName" + ends,
            type: 'get',
            data: parameter,
            dataType: 'json',
            success: function(data)
            {
                if(data && data.status >= 1)
                {
                    data = data.data;
                    $("#client").html("");
                    for(let i = 0; i < data.length; i++)
                    {
                        var tmp = data[i];
                        var flags = tmp.flags;
                        var flagsTmp = [];
                        for(let j = 0; j < flags.length; j++)
                        {
                            flagsTmp.push(flags[j].name);
                        }
                            tmp = tmp.client;

                        $("#client").append(`<tr>
                                <td>${tmp.name?tmp.name:"未知"}</td>
                                <td>${tmp.sex?tmp.sex:"未知"}</td>
                                <td>${tmp.address?tmp.address:"未知"}</td>
                                <td>${flagsTmp.join("、")}</td>
                                <td>${tmp.telephone?tmp.telephone:"未知"}</td>
                                <td>${tmp.email?tmp.email:"未知"}</td>
                                <td>
                                    <button type="button" class="layui-btn layui-btn-small layui-btn-normal clientChatLog" clientId="${tmp.clientId}" clientName="${tmp.name}" name="" id="">聊天记录</button>
                                    <button type="button" class="layui-btn layui-btn-small layui-btn-warm" clientId="${tmp.clientId}" name="" id="">详细信息</button>
                                </td>
                            </tr>`);
                    }
                }
            }
        }
    )
}

$(document).on("click", "#searchClientBtn", function()
    {
        var searchName = $(this).prev().children("input").val();
        addClientTr("searchName=" + searchName);
    }
)

$(document).on("click", ".clientChatLog", function()
    {
        var clientId = $(this).attr("clientId");
        var clientName = $(this).attr("clientName");
        layui.use(['element'], function()
            {
                var element = layui.element();

                var tabTitleDiv = $('.layui-tab[lay-filter="tab"]').children('.layui-tab-title');
                var exist = tabTitleDiv.find('li[lay-id=client-' + clientId + ']');

                if(exist.length <= 0)
                {
                    element.tabAdd('tab',
                        {
                            title: '客户' + clientName + '的聊天记录',
                            content: $('#clientChatLogContent').html(),
                            id: 'client-' + clientId,
                        }
                    );
                }

                element.tabChange('tab', 'client-' + clientId);

                $.ajax(
                    {
                        url: ip + "clientChatLog" + ends,
                        type: 'post',
                        data: "clientId=" + clientId,
                        dataType: 'json',
                        success: function(data)
                        {
                            if(data && data.status >= 1)
                            {
                                data = data.data;
                                var $clientChatLog = $("#clientChatLog");
                                $clientChatLog.html(" ");
                                for(let i = 0; i < data.length; i++)
                                {
                                    var tmp = data[i];
                                    var sendTime = new Date(tmp.time);
                                    $clientChatLog.append(`
                                        <div class="chat-history-item">
                                            <div class="chat-history-item-title">
                                                <span class="chat-history-item-name">${tmp.senderName}&nbsp;&nbsp;&nbsp;</span>
                                                <span class="chat-history-item-time">${[sendTime.getFullYear(), jia0(sendTime.getMonth() + 1), jia0(sendTime.getDate())].join("-") + " " + [jia0(sendTime.getHours()), jia0(sendTime.getMinutes()), jia0(sendTime.getSeconds())].join(":")}</span>
                                            </div>
                                            <div class="chat-history-item-content">
                                                <span>${tmp.content}</span>
                                            </div>
                                        </div>`);
                                }

                                $clientChatLog.scrollTop($clientChatLog.get(0).scrollHeight);
                            }
                        }
                    }
                )
            }
        )
    }
)

var advertisementDetele = [];
var advertisement =
{
    'content': function()
    {
        var content = $("#advertisementContent").html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        addAdvertisement(['advertisement']);
    }
}

function addAdvertisement([searchType, parameter])
{
    $.ajax(
        {
            url: ip + searchType + ends,
            type: 'post',
            data: parameter,
            dataType: 'json',
            success: function(data)
            {
                if(data && data.status >= 1)
                {
                    $("#advertisement").html(" ");
                    data = data.data;
                    for(let i = 0; i < data.length; i++)
                    {
                        var tmp = data[i];
                        var dataFlagList = tmp.flagList;
                        var advertisement = tmp.advertisement;
                        var flagList = [];
                        for(let j = 0; j < dataFlagList.length; j++)
                        {
                            flagList.push(dataFlagList[j].name);
                        }

                        $("#advertisement").append(`<tr>
                            <td><input type="checkbox" name="" lay-skin="primary" lay-filter="advertisement" advId="${advertisement.advId}"></td>
                            <td>黑曜石房地产集团</td>
                            <td>${advertisement.advContent}</td>
                            <td>${flagList.join("、")}</td>
                            <td>2017-07-01</td>
                            <td>2017-07-31</td>
                            <td>
                                <button type="button" class="layui-btn layui-btn-small layui-btn-normal putList" advId="${advertisement.advId}" name="" id="">推送名单</button>
                                <button type="button" class="layui-btn layui-btn-small layui-btn-warm" advId="${advertisement.advId}" name="" id="">详细信息</button>
                            </td>
                        </tr>`);
                    }
                }

                layui.use('form', function()
                    {
                        var form = layui.form();

                        //全选
                        form.on('checkbox(allChooseAdvertisement)', function(data)
                            {
                                advertisementDetele = [];
                                var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
                                child.each(function(index, item)
                                    {
                                        item.checked = data.elem.checked;
                                        if(data.elem.checked)
                                        {
                                            if(index != 0)
                                            {
                                                advertisementDetele.push(
                                                    {
                                                        "id": $(item).attr("advId"),
                                                    }
                                                )
                                            }
                                        }
                                    }
                                );
                                form.render('checkbox');
                            }
                        );

                        form.on('checkbox(advertisement)', function(data)
                            {
                                var advId = $(data.elem).attr("advId");

                                if(data.elem.checked)
                                {
                                    advertisementDetele.push(
                                        {
                                            "id": advId,
                                        }
                                    )
                                }
                                else
                                {
                                    for(let  i = 0; i < advertisementDetele.length; i++)
                                    {
                                        if(advertisementDetele[i].id == advId)
                                        {
                                            advertisementDetele.splice(i, 1);
                                            break;
                                        }
                                    }
                                }
                                form.render('checkbox');
                            }
                        );

                        form.render('checkbox');
                    }
                );
            }
        }
    )
}

$(document).on("click", ".pushList", function()
    {
        var advId = $(this).attr("advId");
        $.ajax(
            {
                url: ip + "advertiseClient" + ends,
                type: 'get',
                data: "advId=" + advId,
                dataType: 'json',
                success: function(data)
                {
                    if(data && data.status >= 1)
                    {
                        data = data.data;

                    }
                }
            }
        )
    }
)

$(document).on("click", "#addAdvertisementBtn", function()
    {
        newKeyWords = [];
        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: "添加广告",
                        content: $("#addAdvertisementContent").html(),
                        area: ['600px', '750px'],
                        btn1: function()
                        {
                            var addAdvertisementForm = $("#addAdvertisementForm").get(0);
                            var content = addAdvertisementForm.content.value;

                            $.ajax(
                                {
                                    url: ip + "addAdvertisement" + ends,
                                    type: 'post',
                                    data:
                                    {
                                        'flag': newKeyWords.join(" "),
                                        'content': content
                                    },
                                    dataType: 'json',
                                    success: function(data)
                                    {
                                        layer.msg(data.message);
                                        flash();
                                    }
                                }
                            )

                            newKeyWords = [];
                            addAdvertisement(['advertisement']);
                        },
                        cancle: function()
                        {
                            newKeyWords = [];
                        }
                    }
                )

                form.render();
            }
        )
    }
)

$(document).on("click", "#deleteAdvertisementBtn", function()
    {
        layui.use(['layer'], function()
            {
                var layer = layui.layer;

                layer.confirm('确认删除？',
                    {
                        icon: 0
                    },
                    function()
                    {
                        $.ajax(
                            {
                                type: 'post',
                                url: ip + "deleteAdv" + ends,
                                dataType: 'json',
                                data: "advList=" + advertisementDetele,
                                success: function(data)
                                {
                                    layer.msg(data.message);
                                }
                            }
                        )

                        advertisementDetele = [];
                        flash();
                    },
                    function()
                    {
                        // layer.msg("no");
                    }
                )
            }
        )
    }
)

$(document).on("click", "#searchAdvertisementBtn", function()
    {
        var searchName = $(this).prev().children("input").val();
        addAdvertisement(['advertisementSearchName', 'searchName=' + searchName]);
    }
)

var noticeObjTypeArray = ['', '全体客服', '客服组', '客服个人'];
var noticeTypeArray = ['', '聊天转接通知', '系统通知', '普通通知'];
var notice =
{
    'content': function()
    {
        var content = $("#noticeContent").html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        addNoticeTr();
    }
}

function addNoticeTr() {
    $.ajax(
        {
            url: ip + "notification" + ends,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                if(data && data.status >= 1)
                {
                    data = data.data;
                    var $notice = $("#notice");
                    $notice.html("");
                    for(let i = 0; i < data.length; i++)
                    {
                        var tmp = data[i];
                        var notification = tmp.notification;
                        var getMessageObject = tmp.getMessageObject;
                        var getMessageName;
                        var not = noticeObjTypeArray[notification.notId];
                        var nt = noticeTypeArray[notification.ntId];
                        var content = notification.content;
                        var time = new Date(notification.time);

                        if(typeof getMessageObject == "object")
                        {
                            getMessageName = getMessageObject.name;
                        }
                        else
                        {
                            getMessageName = getMessageObject;
                        }

                        $notice.append(`
                            <tr>
                                <td>${getMessageName}</td>
                                <td>${not}</td>
                                <td>${nt}</td>
                                <td>${[time.getFullYear(), jia0(time.getMonth() + 1), jia0(time.getDate())].join('-') + ' ' + [jia0(time.getHours()), jia0(time.getMinutes()), jia0(time.getSeconds())].join(':')}</td>
                                <td>${content}</td>
                            </tr>`)
                    }
                }
            }
        }
    )
}

$(document).on("click", "#addNoticeBtn", function()
    {
        initCustomerServiceGroup();
        var customerServiceList = [];
        $.ajax(
            {
                url: ip + "allCustom" + ends,
                type: 'post',
                dataType: 'json',
                success: function(data)
                {
                    if(data && data.status >= 1)
                    {
                        data = data.data;
                        for(let i = 0; i < data.length; i++)
                        {
                            var tmp = data[i];
                            if(customerServiceList[tmp.groupId] == undefined)
                            {
                                customerServiceList[tmp.groupId] = [];
                            }

                            customerServiceList[tmp.groupId].push(
                                {
                                    "serviceId": tmp.serviceId,
                    		        "name": tmp.name,
                                }
                            )
                        }
                    }
                }
            }
        )
        console.log(customerServiceList);

        var noticeObjType;
        var objId;
        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: "创建通知",
                        content: $("#addNoticeContent").html(),
                        area: ['600px', '750px'],
                        btn1: function()
                        {
                            var addNoticeForm = $("#addNoticeForm").get(0);
                            var content = addNoticeForm.content.value;

                            $.ajax(
                                {
                                    url: ip + "addNotification" + ends,
                                    type: 'get',
                                    data:
                                    {
                                        'ntId': 2,
                                        'notId': noticeObjType,
                                        'objectId': objId,
                                        'content': content
                                    },
                                    dataType: 'json',
                                    success: function(data)
                                    {
                                        layer.msg(data.message);
                                        flash();
                                    }
                                }
                            )
                        },
                        cancle: function()
                        {
                            newKeyWords = [];
                        }
                    }
                )

                for(let i = 0; i < noticeObjTypeArray.length; i++)
                {
                    $("#chooseNoticeType").append(`
                        <option value="${i}">${noticeObjTypeArray[i]}</option>`);
                }

                form.render();

                form.on('select(chooseNoticeType)', function(data)
                    {
                        noticeObjType = data.value;
                        var $chooseObjPersonDiv = $("#chooseObjPersonDiv");
                        var $chooseObjGroupDiv = $("#chooseObjGroupDiv");
                        if(noticeObjType == 1)
                        {
                            objId = 1;
                            $chooseObjPersonDiv.hide();
                            $chooseObjGroupDiv.hide();
                        }
                        else if(noticeObjType == 2)
                        {
                            $chooseObjPersonDiv.hide();
                            $chooseObjGroupDiv.show();

                            objId = undefined;
                            $("#chooseObjGroup").html("<option value='' checked></option>");
                            for(let i = 0; i < customerServiceGroup.length; i++)
                            {
                                if(customerServiceGroup[i] != undefined)
                                {
                                    $("#chooseObjGroup").append("<option value=" + i + ">" + customerServiceGroup[i] + "</option>");
                                }
                            }
                        }
                        else if(noticeObjType == 3)
                        {
                            $chooseObjPersonDiv.show();
                            $chooseObjGroupDiv.hide();

                            objId = undefined;
                            var $chooseObjPerson = $("#chooseObjPerson");
                            $chooseObjPerson.html("<option value='' checked></option>");
                            for(let i = 0; i < customerServiceList.length; i++)
                            {
                                if(customerServiceList[i] != undefined)
                                {
                                    $chooseObjPerson.append(`
                                        <optgroup label="${customerServiceGroup[i]}">
                                        </optgroup>`);
                                    var personOptgroup = $chooseObjPerson.find("optgroup[label=" + customerServiceGroup[i] + "]")
                                    for(let j = 0; j < customerServiceList[i].length; j++)
                                    {
                                        var tmp = customerServiceList[i][j];
                                        personOptgroup.append(`
                                            <option value="${tmp.serviceId}">${tmp.name}</option>`)
                                    }
                                }
                            }
                        }

                        form.render();
                    }
                );

                form.on("select(objId)", function(data)
                    {
                        objId = data.value;
                        console.log(objId);
                    }
                )
            }
        )
    }
)

$(document).on("click", "#filterNoticeBtn", function()
    {
        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: '筛选通知',
                        content: $("#filterNoticeContent").html(),
                        area: ['600px', '400px'],
                        btn1: function()
                        {

                        }
                    }
                );

                form.render();
            }
        )
    }
)

var customerServiceDivisionalStructures =
{
    'content': function()
    {
        var content = $("#customerServiceDivisionalStructuresContent").html();
        return content;
    },

    'getData': function()
    {

    },

    'chart': function()
    {
        addCustomerServiceDivisionalStructureDiv();
    }
}

function addCustomerServiceDivisionalStructureDiv()
{
    $("#customerServiceDivisionalStructures").html(" ");

    $.ajax(
        {
            url: ip + "customerServiceManagement" + ends,
            type: 'get',
            dataType: "json",
            success: function (data) {
                if(data && data.status >= 1)
                {
                    data = data.data;
                    for(let i = 0; i < data.length; i++)
                    {
                        var tmp = data[i];
                        var groupWords = tmp.groupWords;
                        var serviceGroup = tmp.serviceGroup;
                        var groupWordsTmp = [];
                        for(let j = 0; j < groupWords.length; j++)
                        {
                            groupWordsTmp.push(groupWords[j].word);
                        }

                        $("#customerServiceDivisionalStructures").append(`
                            <div class="division">
                                <div class="tab"></div>
                                <div class="division-box">
                                    <a href="#">${serviceGroup.name}&nbsp;&nbsp;&nbsp;</a>
                                    <i>${groupWordsTmp.join("、")}</i>
                                </div>
                                <div class="division-btn">
                                    <button class="layui-btn editCustomerServiceDivisionalStructureBtn" groupId="${i}">
                                        <i class="fa fa-pencil"></i>
                                    </button>
                                    <button class="layui-btn layui-btn-danger deleteCustomerServiceDivisionalStructureBtn" groupId="${i}">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </div>
                            </div>`);
                    }
                }
            }
        }
    )
}

$(document).on("click", "#addCustomerServiceGroupBtn", function()
    {
        newKeyWords = [];
        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: '添加组别',
                        content: $("#addCustomerServiceGroupContent").html(),
                        area: ['600px', '400px'],
                        btn1: function () {
                            var addCustomerServiceGroupForm = $("#addCustomerServiceGroupForm").get(0);
                            var groupName = addCustomerServiceGroupForm.groupName.value;

                            $.ajax(
                                {
                                    url: ip + "addGroup" + ends,
                                    type: 'get',
                                    data:
                                        {
                                            'groupName': groupName,
                                            'groupTag': newKeyWords.join(" "),
                                        },
                                    dataType: 'json',
                                    success: function (data) {
                                        if(data && data.status >= 1)
                                        {
                                            layer.msg(data.message);
                                            flash();
                                        }
                                    }
                                }
                            )
                        }
                    }
                )

                form.render();
            }
        )
    }
)

$(document).on("click", ".editCustomerServiceDivisionalStructureBtn", function()
    {
        var $parentDiv = $(this).parents(".division-btn").prev();
        var groupName = $parentDiv.children("a").text();
        newKeyWords = $parentDiv.children("i").text().split("、");

        layui.use(['layer', 'form'], function()
            {
                var layer = layui.layer;
                var form = layui.form();

                layer.open(
                    {
                        title: '添加组别',
                        content: $("#addCustomerServiceGroupContent").html(),
                        area: ['600px', '400px'],
                    }
                )

                var addCustomerServiceGroupForm = $("#addCustomerServiceGroupForm").get(0);
                console.log(addCustomerServiceGroupForm.groupName);
                addCustomerServiceGroupForm.groupName.value = groupName;
                appendKeyWord();

                form.render();
            }
        )
    }
)

$(document).on("click", ".deleteCustomerServiceDivisionalStructureBtn", function ()
    {
        var groupId = $(this).attr("groupId");
        layui.use(['layer'], function()
            {
                var layer = layui.layer;

                layer.confirm('确认删除？',
                    {
                        icon: 0
                    },
                    function()
                    {
                        $.ajax(
                            {
                                type: 'get',
                                url: ip + "deleteGroupById" + ends,
                                dataType: 'json',
                                data: "groupId=" + groupId,
                                success: function(data)
                                {
                                    layer.msg(data.message);
                                    flash();
                                }
                            }
                        )

                        advertisementDetele = [];
                    },
                    function()
                    {
                        // layer.msg("no");
                    }
                )
            }
        )
})
