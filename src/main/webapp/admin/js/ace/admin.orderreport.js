jQuery.order = {
		userDataTable:null,
		initSearchDataTable : function() {
			if (this.userDataTable == null) {
				this.userDataTable = $('#dt_table_view').dataTable({
					"sDom" : "<'row-fluid'<'span5'l><'span3'r><'span4'T>t<'row-fluid'<'span6'i><'span6'p>>",
					 "oTableTools": {
				        	 "aButtons": [
				             "copy",
				             "xls"
				             ],
				            "sSwfPath": "media/swf/copy_csv_xls_pdf.swf"
				        },
					"oLanguage" : {
						"sLengthMenu" : "每2页显示 _MENU_ 条记录",
						"sZeroRecords" : "抱歉， 暂时没有记录",
						"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
						"sInfoEmpty" : "没有数据",
						"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : "前一页",
							"sNext" : "后一页",
							"sLast" : "尾页"
						}
					},
			"iDisplayLength" : 500,
			"aLengthMenu" : [ 10, 25, 50, 100,500 ],
			"bServerSide" : true,
			"sServerMethod" : "GET",
			"bProcessing" : true,
			"bRetrieve" : true,
			"bDestory" : true,
			"bAutoWidth" : false,
			"bSort" : false,
			"bFilter":false, 
					"sAjaxSource" : $.ace.getContextPath() + "/admin/order/listreport",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var name = $("#_name").val();
						var start = $("#_start").val();
						var end = $("#_end").val();
						var state = $("#_state").val();
						if (!!name) {
							aoData.push({
								"name" : "name",
								"value" : name
							});
						}
						if (!!start) {
							aoData.push({
								"name" : "start",
								"value" : start
							});
						}
						if (!!end) {
							aoData.push({
								"name" : "end",
								"value" : end
							});
						}
						if (!!state) {
							aoData.push({
								"name" : "state",
								"value" : state
							});
						}
						$.ajax({
							"dataType" : 'json',
							"type" : "POST",
							"url" : sSource,
							"data" : aoData,
							"success" : function(data){
								fnCallback(data);
							}
						});
					},
					"aoColumns" : [{
						"mDataProp" : "id"
					}, {
						"mDataProp" : "user.name"
					},{
						"mDataProp" : "project.name"
					},{
						"mDataProp" : "orderDate"
					},{
						"mDataProp" : "addr"
					},{
						"mDataProp" : "price"
					},{
						"mDataProp" : "toalprice"
					},{
						"mDataProp" : "state"
					},{
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [7],
							'fnRender' : function(oObj, sVal) {
								return " <span class='label label-success'>"+sVal+"</span>";
							}
						},
						{
							'aTargets' : [5],
							'fnRender' : function(oObj, sVal) {
								return "<span class='badge'>"+sVal+"</span></a>";
							}
						},	
						{
							'aTargets' : [6],
							'fnRender' : function(oObj, sVal) {
								return "<span class='badge'>"+sVal+"</span></a>";
							}
						},	
						{
							'aTargets' : [8],
							'fnRender' : function(oObj, sVal) {
								return" <button class=\"btn2 btn-info\" onclick=\"$.order.pass("+oObj.aData.id+")\"><i class=\"icon-edit\"></i> 审核通过</button> " +
										" <button class=\"btn2 btn-info\" onclick=\"$.order.deleteUser("+oObj.aData.id+")\"><i class=\"icon-trash\"></i> 删除</button>";
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.userDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.userDataTable.fnDraw(oSettings);
			}

		},
		deleteUser :function(id){
			bootbox.confirm( "是否确认删除？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/order/delete/"+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.state=='success'){
	        					noty({"text":""+ json.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.order.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		pass :function(id){
			bootbox.confirm( "是否确认审核通过？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/order/pass/"+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.state=='success'){
	        					noty({"text":""+ json.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.order.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		showUserAddModal: function(id){
			$("#id").val(id);
			$('#_modal').modal({
			});
			$("#_modal").modal('show');
		},
		showEdit: function (id){
			$("#id").val(id);
			$.ajax({
    			type : "get",
    			url : $.ace.getContextPath() + "/admin/order/get/"+id,
    			dataType : "json",
    			success : function(json) {
    				if(json.state=='success'){
    					$("#name").val(json.object.name);
    					$("#remark").val(json.object.remark);
    				}else{
    					noty({"text":""+ json.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
			$("#_modal").modal('show');
		},
		
		saveUser: function(id){
			$.ajax({
    			type : "post",
    			url : $.ace.getContextPath() + "/admin/order/save",
    			data:$("form").serialize(),
    			dataType : "json",
    			success : function(json) {
    				if(json.state=='success'){
    					$("#_modal").modal('hide');
    					noty({"text":""+ json.msg +"","layout":"top","type":"success","timeout":"2000"});
    					$.order.initSearchDataTable();
    				}else{
    					noty({"text":""+ json.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		}
		
};