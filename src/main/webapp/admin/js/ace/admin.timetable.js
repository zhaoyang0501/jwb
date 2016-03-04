jQuery.timetable = {
		userDataTable:null,
		initSearchDataTable : function() {
			if (this.userDataTable == null) {
				this.userDataTable = $('#dt_table_view').dataTable({
					"sDom" : "<'row-fluid'<'span6'l>r>t<'row-fluid'<'span6'i><'span6'p>>",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "每页显示 _MENU_ 条记录",
						"sZeroRecords" : "抱歉， 暂时没有记录",
						"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
						"sSearch" : "",
						"sInfoEmpty" : "没有数据",
						"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : "前一页",
							"sNext" : "后一页",
							"sLast" : "尾页"
						}
					},
					"bAutoWidth" : false,
					"iDisplayLength" : 10,
					"aLengthMenu" : [ 5, 10, 25, 50],
					"bServerSide" : true,
					"sServerMethod" : "POST",
					"bProcessing" : true,
					"bSort" : false,
					"sAjaxSource" : $.ace.getContextPath() + "/admin/timetable/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var name = $("#_grade").val();
						if (!!name) {
							aoData.push({
								"name" : "gradeid",
								"value" : name
							});
						}
						$.ajax({
							"dataType" : 'json',
							"type" : "POST",
							"url" : sSource,
							"data" : aoData,
							"success" : function(data){
								fnCallback(data);
								//jQuery.timetable.table_rowspan("#dt_table_view",0);
							}
						});
					},
					"aoColumns" : [{
						"mDataProp" : "week"
					},{
						"mDataProp" : "begin"
					},{
						"mDataProp" : "end"
					},{
						"mDataProp" : "category.name"
					},{
						"mDataProp" : "teacher.name"
					},{
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [0],
							'fnRender' : function(oObj, sVal) {
								return  " 星期"+sVal;
							}
						},
						
						{
							'aTargets' : [5],
							'fnRender' : function(oObj, sVal) {
								return" <button class=\"btn2 btn-info\" onclick=\"$.timetable.showEdit("+oObj.aData.id+")\"><i class=\"icon-edit\"></i> 修改</button>  <button class=\"btn2 btn-info\" onclick=\"$.timetable.deleteUser("+oObj.aData.id+")\"><i class=\"icon-trash\"></i> 删除</button>";
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
	        			url : $.ace.getContextPath() + "/admin/timetable/delete/"+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.state=='success'){
	        					noty({"text":""+ json.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.timetable.initSearchDataTable();
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
    			url : $.ace.getContextPath() + "/admin/timetable/get/"+id,
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
    			url : $.ace.getContextPath() + "/admin/timetable/save",
    			data:$("form").serialize(),
    			dataType : "json",
    			success : function(json) {
    				if(json.state=='success'){
    					$("#_modal").modal('hide');
    					noty({"text":""+ json.msg +"","layout":"top","type":"success","timeout":"2000"});
    					$.timetable.initSearchDataTable();
    				}else{
    					noty({"text":""+ json.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		},
		table_rowspan :function(table_id, table_colnum) {
            table_firsttd = "";
            table_currenttd = "";
            table_SpanNum = 0;
            colnum_Obj = $("#dt_table_view tr td:nth-child(1)");
            colnum_Obj1 = $("#dt_table_view tr td:nth-child(2)");
            colnum_Obj3 = $("#dt_table_viewtr td:nth-child(3)");
            alert("colnum_Obj"+colnum_Obj.length);
            colnum_Obj.each(function (i) {
            	alert(i);
                if (i == 0) {
                    table_firsttd = $(this);
                    table_SpanNum = 1;
 
                } else {
                    table_currenttd = $(this);
                    if (table_firsttd.text() == table_currenttd.text()&&colnum_Obj1.eq(i-1).text()==colnum_Obj1.eq(i).text()&&colnum_Obj3.eq(i-1).text()==colnum_Obj3.eq(i).text()) {
                        table_SpanNum++;
                        table_currenttd.hide(); 
                        table_firsttd.attr("rowSpan", table_SpanNum);
                    } else {
                        table_firsttd = $(this);
                        table_SpanNum = 1;
                    }
                }
            });
        }
};