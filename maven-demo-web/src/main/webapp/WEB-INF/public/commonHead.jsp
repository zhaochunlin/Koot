<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>BOSS v1.0</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script type="text/javascript" src="${ctx}/static/ckeditor/ckeditor.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="${ctx}/static/ckeditor/samples/sample.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="${ctx}/static/css/ext-all-debug.css"/> --%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/alex_extend_module_btn.css"/>
<script type="text/javascript" src="${ctx}/static/ext-all.js"></script>
<script type="text/javascript" src="${ctx}/static/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var createId = "${sessionScope.user.employeeId}";//当前登录销售员ID
	var saleEmpName = "${sessionScope.user.name}";//当前登录销售员姓
	var saleEmpLoginName = "${sessionScope.user.loginName}";//当前登录销售员姓名
	var permissions = '${permissions}';
	Ext.namespace("boss.app");
	Ext.namespace("boss.sales");
	Ext.namespace("boss.sales.ui");
	Ext.namespace("boss.sales.business");
	
	Ext.namespace("boss.common.func");	// 公共函数
	//头部header--------------start----------------------------------//
	var eventBorderGreen = "#4FC95D"; //绿色
	var eventBorderGrey = "#ECECEC"; //灰色
	var borderBasicColor = "#F0F0EE";//边框的基调色
	var backgroundColor="#3089D9";
	var backgroundColor_middle="#0093D5";
	var footTopBorderColor = "#B0B0B0";
	//var backgroundColor="#1A9EF2";
	var bodyBackGroundColor = "#FFFFFF";
	var cmColor = "#F4F4F4";
	var resrWidth=window.screen.width-18-1000
	var bodyPaddingR=resrWidth/2;
	var bodyPadding = "0 "+bodyPaddingR+" 0 "+bodyPaddingR;
	//----------------------------------------搜索正式合同start-------------------------------------------------//
	//客户名称
	var contractList_customerName = new Ext.form.field.Text({
		emptyText:'输入客户名称搜索合同',
		width:180,
		height:27,
		margin:"1 0 0 0"
	});
	var contractListSearchCustomerName;//记录查询值
	//查询按钮
	var contractList_customerName_searchBtn = new Ext.panel.Panel({
				height:45,	
				width:50,
				margin:"0 0 0 -5",
				border:0,
				html:"<span style='cursor:pointer' onclick='javascript:contractList_customerName_searchBtn_function();'><img src='"+ctx+"/static/images/newImages/search.jpg'/></span>"
			});
	//查询区域面板
	var  contractList_searchBycustomerNameFormPanel = new Ext.form.Panel({
		height:40,
		width:240,
		border:0,
		layout:"hbox",
		bodyPadding:"3 0 0 5",
		items:[
			contractList_customerName,
			contractList_customerName_searchBtn
		]
	});
	//搜索按钮的函数
	function  contractList_customerName_searchBtn_function(){
		contractListSearchCustomerName = contractList_customerName.getValue();
		boss.sales.ui.workspace.removeAll();
		initContractListPanel(undefined,true);
		boss.sales.ui.workspace.add(MyContractMainPanel);
		//加载数据
		loadContractList(-1,undefined,true);
	}

//----------------------------------------搜索正式合同end-------------------------------------------------//
	this.bodyPadding = bodyPadding;
	boss.sales.ui.headerRight = new Ext.panel.Panel({
        layout:"hbox",
        height: 70,
        width:800,
        border:0
	});
  	boss.sales.ui.headerLogo = new Ext.panel.Panel({
        height: 70,
        border:0,
        width:200,
        html:"<div class='bktop'><img src='${ctx}/static/images/newImages/logo.png'/></div>"
    });
    boss.sales.ui.headermain = new Ext.panel.Panel({
    	layout:"hbox",
    	border:0,
        height: 70,
        bodyPadding:bodyPadding
    });
    //-----header菜单---------------------------//
    
    //---------中间部分----------------------------//
    boss.sales.ui.middleLeft = new Ext.panel.Panel({
    	layout:"hbox",
        height: 40,
        width:800,
        border:0,
    	bodyStyle:{
          	background:backgroundColor_middle
        } 
    });
    boss.sales.ui.middleRight = new Ext.panel.Panel({
        height: 40,
        width:200,
        border:0,
    	bodyStyle:{
          	background:backgroundColor_middle,
          	textAlign:"right"
        },
        bodyPadding:"10 5 0 0",
        html:"<span class='baseFontClsWihte'><shiro:principal/>&nbsp;&nbsp;|&nbsp;&nbsp;"
			+"<span class='baseFontClsWihteWithCursor' onclick='javascript:window.location = \"${ctx}/logout/\"\'>退出</span></span>"
    });
	boss.sales.ui.middleMainCentre = new Ext.panel.Panel({
		  layout:"hbox",
		  height:40,
		  border:0,
		  width:1000,
		  bodyStyle:{
	          	background:backgroundColor_middle
	      }
	});
  	boss.sales.ui.middleMain = new Ext.panel.Panel({
        height: 40,
        bodyPadding:"0 "+bodyPaddingR+" 0 "+bodyPaddingR,
        border:0,
    	bodyStyle:{
          	background:backgroundColor_middle
        }
    });
    
    //---------中间部分----------------------------//
    
  //头部header--------------end----------------------------------//
    boss.sales.ui.workspace = new Ext.panel.Panel({
    	border:0,
    	width:1000,
    	height:700
    	/* style:{
    		borderLeft:"1px solid "+borderBasicColor,
    		borderRight:"1px solid "+borderBasicColor
    	}  */
    });
  
    boss.sales.ui.workspacemain = new Ext.panel.Panel({
    	border:0,
    	bodyStyle:{
          	background:bodyBackGroundColor
         },
        cls:"workspace-div",
    	height:700,
    	bodyPadding:bodyPadding
    });
    
    //脚部
    boss.sales.ui.foot = new Ext.panel.Panel({
    	border:0,
    	width:1000,
    	height:50,
    	bodyPadding:"10 0 0 140",
    	style:{
    		borderTop:"1px solid "+footTopBorderColor
    	}, 
    	html:'<font color="#0E0D0D">创想空间商务通信服务有限公司/G-Net Integrated Services Co., Ltd. 客服热线: 400-810-1919 Email: service@quanshi.com</font>'
    });
    boss.sales.ui.footmain = new Ext.panel.Panel({
    	layout:"hbox",
    	border:0,
    	bodyPadding:"0 0 0 "+bodyPaddingR,
    	height:50
    });
    
    boss.sales.ui.home = new Ext.panel.Panel({
    	layout:"vbox",
    	overflowX:"scroll",
    	overflowY:"scroll",
    	border:0
    });
    
    //退出
    boss.app.logout = function(){
    	window.location = "${ctx}/logout/";
    };
    
    function initHome() {
    	//设置不同浏览器的宽度
    	if(Ext.isIE) {
    		boss.sales.ui.headermain.setWidth(window.screen.width-18);
    		boss.sales.ui.middleMain.setWidth(window.screen.width-18);
    		boss.sales.ui.workspacemain.setWidth(window.screen.width-18);
    		boss.sales.ui.home.setWidth(window.screen.width-18);
    	}else if(Ext.isChrome) {
    		boss.sales.ui.headermain.setWidth(window.screen.width-16);
    		boss.sales.ui.middleMain.setWidth(window.screen.width-16);
    		boss.sales.ui.workspacemain.setWidth(window.screen.width-16);
    		boss.sales.ui.home.setWidth(window.screen.width-16);
    	}else if((Ext.isGecko)) {
    		boss.sales.ui.headermain.setWidth(window.screen.width-17);
    		boss.sales.ui.middleMain.setWidth(window.screen.width-17);
    		boss.sales.ui.workspacemain.setWidth(window.screen.width-17);
    		boss.sales.ui.home.setWidth(window.screen.width-17);
    	}else {
    		boss.sales.ui.headermain.setWidth(window.screen.width-20);
    		boss.sales.ui.middleMain.setWidth(window.screen.width-20);
    		boss.sales.ui.workspacemain.setWidth(window.screen.width-20);
    		boss.sales.ui.home.setWidth(window.screen.width-20);
    	}
    	boss.sales.ui.headermain.add(boss.sales.ui.headerLogo); 
    	boss.sales.ui.headermain.add(boss.sales.ui.headerRight);
    	boss.sales.ui.middleMainCentre.add(boss.sales.ui.middleLeft);
    	boss.sales.ui.middleMainCentre .add(boss.sales.ui.middleRight);
    	boss.sales.ui.middleMain.add(boss.sales.ui.middleMainCentre);
    	boss.sales.ui.workspacemain.add(boss.sales.ui.workspace);
    	boss.sales.ui.footmain.add(boss.sales.ui.foot);
    	var fixedPanel = Ext.create("Ext.panel.Panel",{
        	layout:"vbox",
        	border:0,
        	cls:"ntes-nav"
        });
    	fixedPanel.add(boss.sales.ui.headermain);
    	fixedPanel.add(boss.sales.ui.middleMain);
    	boss.sales.ui.home.add(fixedPanel);
    	boss.sales.ui.home.add(boss.sales.ui.workspacemain);
    	boss.sales.ui.home.add(boss.sales.ui.footmain);
    	return boss.sales.ui.home;
    }
    //验证价格
 	 //判断是否正确填入数字的标志
    var validatePriceResult = true; 
    var validatePriceFailMsg;
    //验证价格填入的数字的正确性的函数
    var validatePrice = function(obj){
    	var value = obj.value;
    	validatePriceFailMsg = verifyTextField(value);
    	if(validatePriceFailMsg!=null){
    		  validatePriceResult = false;
    		  obj.setAttribute("class","textFiledBorder");
    		  msgAlert("资费价格验证",validatePriceFailMsg);
    	}else {
    		validatePriceResult = true;
    		obj.setAttribute("class","");
    		obj.setAttribute("value",value);
    		
    	}
    }

</script>
<script type="text/javascript" src="${ctx}/app/functions/CommonFunctions.js?rd=<%=Math.random()%>"></script>
<script type="text/javascript" src="${ctx}/static/sys_constants.js?rd=<%=Math.random()%>"></script>
