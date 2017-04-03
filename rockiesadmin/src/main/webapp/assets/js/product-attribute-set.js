
function appendProductStock($label,val){
	$label.append(
			'<div class="col-lg-12">'+
				'<div class="col-lg-12">'+
					'<div class="action-buttons">'+
						'<label for="form-field-8">库存</label>'+
						'<input type="hidden" id="subProducts" name="subProducts" > '+
						'<input class="form-control" id="stock" name="stock" placeholder="请输入库存"> '+
					'</div>'+
				'</div>'+
			'</div>'+
			'<script type="text/javascript">'+
			'jQuery(function($){'+
				'$("#stock").val('+val+');'+
			'});'+
			'</script>'
	);
}

function appendProductSku($label,val){
	$label.append(
			/*'<div class="col-lg-12">'+
				'<div class="col-lg-12">'+
					'<div class="action-buttons">'+
						'<label for="form-field-8">商家货号</label>'+
						'<input id="categoryId" name="categoryId" type="hidden" value="0">'+
						'<input id="isMulti" name="isMulti" type="hidden" value="0">'+
						'<input class="form-control" id="sku" name="sku" placeholder="请输入商家货号"> '+
					'</div>'+
				'</div>'+
			'</div>'+
			'<script type="text/javascript">'+
			'jQuery(function($){'+
				'$("#sku").val('+val+');'+
				'$("#categoryId").val($("#categoriesThird").val());'+
			'});'+
			'<\/script>'*/
	);
}

function appendProductText($label,obj){
	var val = '';
	$label.append(
			'<div class="col-lg-12">'+
				'<div class="col-lg-12">'+
					'<div class="action-buttons">'+
						'<label for="form-field-8">'+obj.displayName+'</label>'+
						'<input class="form-control" id="'+obj.attrCode+'" name="productAttributeMap['+obj.attrCode+']" placeholder="请输入'+obj.displayName+'" value="'+val+'"> '+
					'</div>'+
				'</div>'+
			'</div>'+
			'<script type="text/javascript">'+
			//'jQuery(function($){'+
				'$("#'+obj.attrCode+'").val('+val+');'+
			//'});'+
			'</script>'
	);
}

function appendProductTextarea($label,obj){
	var val = '';
	$label.append(
		'<div class="col-lg-12">&nbsp;</div>'+
		'<div class="col-lg-12" >'+
			'<div class="col-lg-12" >'+
				'<label for="form-field-8">'+obj.displayName+'</label>'+
				'<input id="'+obj.attrCode+'" name="productAttributeMap['+obj.attrCode+']" type="hidden">'+
				'<script type="text/plain" id="'+obj.attrCode+'val" style="width:80%;height:200px;"></script>'+
			'</div>'+
		'</div>'+
		'<script type="text/javascript">'+
			//'jQuery(function($){'+
				'var um = UM.getEditor("'+obj.attrCode+'val");	'+
				'um.addListener("blur",function(){'+
					'$("#'+obj.attrCode+'").val(um.getContent());'+
			    '});'+
			//'});'+
		'</script>'
	);
}

function appendProductSelect($label,obj,basePath){
	var val = 0;
	var selectVal = "";
	var javascriptVal = "";
	if(obj.displayName == 'store_group_id'){
		return;
	}
	
	if(obj.isMultiSelect == 1){
		
	}
	
	if(obj.isBoolean == 1){
		selectVal = 
			'<option value="0">否</option>'+
			'<option value="1">是</option>';
	}else{
		if(obj.attrCode == 'status'){
			selectVal = 
				'<option value="3" >立即提交审核</option>'+
				'<option value="2" >放入商品仓库</option>'
				;
		}else{
			javascriptVal = 
				'<script type="text/javascript">'+
					'jQuery(function($){'+
						'var url = "'+basePath+'attributeList.html";'+
						'$.ajax({'+
							'type:"GET",'+
							'url: url,'+
							'data:{attrId: '+obj.attrId+'},'+
							'dataType: "html",'+
							'success: function(json){'+
								'var result =jQuery.parseJSON(json);'+
								'var rows = result.rows;'+
								'$(rows).each(function(index){'+
									'$("#'+obj.attrCode+'").append('+
										'"<option value="+rows[index].id+">"+rows[index].value+"</option>"'+
									');'+
								'});'+
							'}'+
						'});'+
					'});'+
				'<\/script>';
		}
	}
	
	$label.append(
			'<div class="col-lg-12">'+
				'<div class="col-lg-4">'+
					'<div class="action-buttons">'+
						'<label for="form-field-8">'+obj.displayName+'</label>'+
						'<select class="form-control" id="'+obj.attrCode+'" name="productAttributeMap['+obj.attrCode+']">'+
							selectVal+
						'</select> '+
					'</div>'+
				'</div>'+
				'<div class="col-lg-4"></div>'+
				'<div class="col-lg-4"></div>'+
			'</div>'+
			javascriptVal
	);
	
}

function appendMultiProductSelect($label,obj,basePath){
	$label.append(
			'<div class="form-group" >'+
				'<label>'+obj.displayName+'</label> '+
				'<select class="form-control multi-product-type" id="'+obj.attrCode+'" name="'+obj.attrCode+'" >'+
                '</select>'+
			'</div>'+
			'<div class="form-group">'+
				'<label>差价</label> '+
				'<input class="form-control" id="'+obj.attrCode+'_price" name="'+obj.attrCode+'" type="text">'+
			'</div>'+
			'<script type="text/javascript">'+
				'jQuery(function($){'+
					'var url = "'+basePath+'attributeList.html";'+
					'$.ajax({'+
						'type:"GET",'+
						'url: url,'+
						'data:{attrId: '+obj.attrId+'},'+
						'dataType: "html",'+
						'success: function(json){'+
							'var result =jQuery.parseJSON(json);'+
							'var rows = result.rows;'+
							'$(rows).each(function(index){'+
								'$("#'+obj.attrCode+'").append('+
									'"<option value="+rows[index].id+">"+rows[index].value+"</option>"'+
								');'+
							'});'+
						'}'+
					'});'+
				'});'+
			'<\/script>'
		);
	
}

function appendMultiProductSelectToSkuAndStock($label){
	$label.append(
			'<div class="hr hr8 hr-double"></div>'+
			'<div class="form-group">'+
				'<label>商品sku ：</label> '+
				'<input class="form-control multi-product-type" id="sku_multi" name="sku" type="text">'+
			'</div>'+
			'<div class="form-group">'+
				'<label>库存 ：</label> '+
				'<input class="form-control multi-product-type" id="stock_multi" name="stock" type="text">'+
			'</div>'
		);
}

function appendProductDate($label,obj){
	var val = '';
	$label.append(
			'<div class="col-lg-12">'+
			'<div class="col-lg-4">'+
				'<div class="action-buttons">'+
					'<label for="form-field-8">'+obj.displayName+'</label>'+
					'<div class=" input-group " >'+
						'<input class="form-control date-picker" id="'+obj.attrCode+'" name="productAttributeMap['+obj.attrCode+']" type="text" />'+
						'<span class="input-group-addon">'+
							'<i class="fa fa-calendar bigger-110"></i>'+
						'</span>'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="col-lg-4"></div>'+
			'<div class="col-lg-4"></div>'+
		'</div>'+
		'<script type="text/javascript">'+
		'jQuery(function($){'+
			'$(".date-picker").datepicker({'+
	       	   'format: "yyyy-mm-dd",'+
	           'autoclose: true,'+
	           'todayHighlight: true'+
	       	'});'+
		'});'+
		'</script>'
	);
	
}

function appendProductImage($label,obj,basePath){
	var val = '';
	
	if(obj.attrCode == 'thumbnail' || obj.attrCode == 'small_image'){
		return;
	}else{
		$label.append(
			'<input id="'+obj.attrCode+'" name="productAttributeMap['+obj.attrCode+']" type="hidden">'
		);
		var $image = $("#imageLebel");
		$image.append(
				'<div class="row">'+
				'<div class="col-lg-12">'+
				'<form action="'+basePath+'upload.html" id="'+obj.attrCode+'FileForm" method="post" enctype="multipart/form-data">'+
					'<div class="form-group"><!-- 主图地址 -->'+
						'<label class="col-lg-1 control-label no-padding-right" for="form-field-1" >'+obj.displayName+'</label>'+
						'<div class="col-sm-11">'+
							'<div class="defaultImg col-sm-8">'+
								'<input type="file" id="'+obj.attrCode+'File"  class="id-input-file-2" name="file" value="111" style="display: block; border: 1px solid #333;"/>'+
								'<input name="name" id="'+obj.attrCode+'Name" type="hidden">'+
							'</div>'+
							'<div class="col-sm-2">'+
								'<button id="'+obj.attrCode+'Btn" type="button" class="btn btn-sm btn-success" ><i class="fa fa-upload" ></i> 上传 </button>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</form>'+
			'</div>'+
			'<div class="col-lg-12">&nbsp;</div>'+
			'<div class="col-lg-12">'+
				'<ul class="ace-thumbnails clearfix">'+
					'<li>'+
						'<a href="'+basePath+'assets/u210.png" id="'+obj.attrCode+'Href" title="'+obj.displayName+'" data-rel="colorbox">'+
							'<img width="150" height="150" alt="150x150" id="'+obj.attrCode+'ReviewImage" src="'+basePath+'assets/u210.png" />'+
							'<div class="text">'+
								'<div class="inner">'+obj.displayName+' </div>'+
							'</div>'+
						'</a>'+
					'</li>'+
				'</ul>'+
			'</div>'+
			'<div class="col-lg-12">请上传*.jpg,*.bmp,*.png文档，分辨率&lt;800x600，大小&lt;600K的图片文件</div>'+
		'</div>'+
		'<script type="text/javascript">'+
		'jQuery(function($){'+
			'$(\'.id-input-file-2\').ace_file_input({'+
		        'no_file: \'No File ...\','+
		        'btn_choose: \'Choose\','+
		        'btn_change: \'Change\','+
		        'droppable: false,'+
		        'onchange: null,'+
		        'thumbnail: false'+
		    '});'+
		    '$("#'+obj.attrCode+'Btn").click(function(){'+
				'var imgpath = $("#'+obj.attrCode+'File");'+
				'$("#'+obj.attrCode+'Name").val(imgpath.val());'+
				'$("#'+obj.attrCode+'FileForm").ajaxSubmit({'+
		            'url:\''+basePath+'upload.html\','+
		            'type:\'POST\','+
		            'dataType:\'json\','+
		            'success: function(obj){'+
		                '$("#'+obj.attrCode+'ReviewImage").attr("src",obj.data.originUrl);'+
		                '$("#'+obj.attrCode+'Href").attr("href",obj.data.originUrl);'+
		                '$("#'+obj.attrCode+'").val(obj.data.originUrl);'+
		            '} '+
		        '});'+
			'});'+
		'});'+
		'imgColorBox();'+
		'</script>'
		);
	}
}

function appendProductGallery($label,obj,basePath){
	var val = '';
	var imageVal = "";
	var imageReviewVal = "";
	var imageJavascript = "";
	var imageHidden = "";
	var imageIndex = 5;
	$label.append(
			'<input id="'+obj.attrCode+'" name="productAttributeMap['+obj.attrCode+']" type="hidden">'
		);
	
	for(var i = 1 ; i <= imageIndex;i++){
		imageVal += 
			'<div class="col-lg-12">'+
				'<form action="'+basePath+'upload.html" id="images'+i+'FileForm" method="post" enctype="multipart/form-data">'+
					'<div class="form-group"><!-- 附图'+i+' -->'+
						'<label class="col-lg-1 control-label no-padding-right" for="form-field-1" >附图'+i+'： </label>'+
						'<div class="col-sm-11">'+
							'<div class="col-sm-8">'+
								'<input type="file" id="images'+i+'File" class="id-input-file-2" name="file" style="display: block; border: 1px solid #333;"/>'+
								'<input name="name" id="images'+i+'Name" type="hidden">'+
								'<input name="images'+i+'" id="images'+i+'" type="hidden">'+
							'</div>'+
							'<div class="col-sm-2">'+
								'<button id="images'+i+'ImgBtn" type="button" class="btn btn-sm btn-success" ><i class="fa fa-upload" ></i> 上传 </button>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</form>'+
			'</div>';
			
		imageReviewVal += 
			'<li>'+
				'<a href="'+basePath+'assets/u210.png" id="images'+i+'Href" title="附图'+i+'" data-rel="colorbox">'+
					'<img width="150" height="150" alt="150x150" id="images'+i+'ReviewImage" src="'+basePath+'assets/u210.png" />'+
					'<div class="text">'+
						'<div class="inner">附图'+i+' </div>'+
					'</div>'+
				'</a>'+
			'</li>';
			
		imageJavascript +=
			'$("#images'+i+'ImgBtn").click(function(){'+
				'var imgpath = $("#images'+i+'File");'+
				'$("#images'+i+'Name").val(imgpath.val());'+
				'$("#images'+i+'FileForm").ajaxSubmit({'+
		            'url:\''+basePath+'upload.html\','+
		            'type:\'POST\','+
		            'dataType:\'json\','+
		            'success: function(obj){'+
		                '$("#images'+i+'ReviewImage").attr("src",obj.data.originUrl);'+
		                '$("#images'+i+'Href").attr("href",obj.data.originUrl);'+
		                '$("#images'+i+'").val(obj.data.originUrl);'+
		                'getImagesVal();'+
		            '} '+
		        '});'+
			'});';
		
		if(i == 1){
			imageHidden += 'var images'+i+' = $("#images'+i+'").val() == \'\' ? \'\':$("#images'+i+'").val();';
		}else{
			imageHidden += 'var images'+i+' = $("#images'+i+'").val() == \'\' ? \'\':","+$("#images'+i+'").val();';
		}
		
	}
	
	var $image = $("#imageLebel");
	$image.append(
		'<div class="row">'+
			imageVal+
			'<div class="col-lg-12">&nbsp;</div>'+
			'<div class="col-lg-12">'+
				'<ul class="ace-thumbnails clearfix">'+
					imageReviewVal+
				'</ul>'+
			'</div>'+
			'<div class="col-lg-12">请上传*.jpg,*.bmp,*.png文档，分辨率&lt;800x600，大小&lt;600K的图片文件</div>'+
		'</div>'+
		'<script type="text/javascript">'+
			'function getImagesVal(){'+

				'$("#'+obj.attrCode+'").val(\'\');'+
				imageHidden+
			    
			    '$("#'+obj.attrCode+'").val(images1+images2+images3+images4+images5);'+
			'}'+
			'jQuery(function($){'+
				'getImagesVal();'+
				'$(\'.id-input-file-2\').ace_file_input({'+
			        'no_file: \'No File ...\','+
			        'btn_choose: \'Choose\','+
			        'btn_change: \'Change\','+
			        'droppable: false,'+
			        'onchange: null,'+
			        'thumbnail: false'+
			    '});'+
			    imageJavascript+
			    'imgColorBox();'+
			'});'+
		'</script>'
	);
}

//-------------------------------------------------------------------------------//

function imgColorBox(){
	var $overflow = '';
	var colorbox_params = {
		rel: 'colorbox',
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="ace-icon fa fa-arrow-left"></i>',
		next:'<i class="ace-icon fa fa-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			$overflow = document.body.style.overflow;
			//document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = $overflow;
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
}

function showErrorAlert (reason, detail) {
	var msg='';
	if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
	else {
		//console.log("error uploading file", reason, detail);
	}
	$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
	 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
}

function checkDupConfig(configValue, getRows) {
	var p;
	return getRows.every(function (x) {
		var attr = x.attrValue;
		for (p in configValue) {
			if (!configValue.hasOwnProperty(p)) continue;
			if (configValue[p].value != attr[p].value)
				return true;
		}
		return false;
	});
}

function updatePrice(configValue, getRows) {
	var p;
	return getRows.forEach(function (x) {
		var attr = x.attrValue;
		for (p in configValue) {
			if (!configValue.hasOwnProperty(p)) continue;
			if (configValue[p].value != attr[p].value) continue;
			attr[p].price = configValue[p].price;
		}
	});
}

function initPrice(code, opt) {
	var getRows = $('#tableInfo').bootstrapTable('getData'), i, v;
	for (i in getRows) {
		if (!getRows.hasOwnProperty(i)) continue;
		v = getRows[i].attrValue[code];
		if (v !== undefined && v.value == opt) {
			return v.price;
		}
	}
}
