<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.Base64"%>
<html>
<head>
<!-- Bootstrap CDN(Content Delivery Network) link to get support of BootStrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
	
	<!-- this is javaScrip validation code location -->
	<script src="formValidation.js"></script>

<title>Edit Product Details</title>
</head>
<body>
	<div class="container mt-5 text-center">	
	<h2 class = "text-center font-italic mb-1">Save Product Data...</h2>	
		 <div class="container mt-5 text-center">
		<form action="./UpdateProductServlet" method="post"
		enctype="multipart/form-data" onsubmit="return validateform()">
		
		<div class="form-group">
			<label class = "font-italic font-weight-bold"
			for="proId">Product ID:</label>
			<input type = "text"class = "form-control-sm" id="ProID"
			name="proId" value="${ExisistingData.proid }" required>
		</div>
			
		<div class="form-group">
			<label class = "font-italic font-weight-bold"
			for="proId">Product Name:</label>
			<input type = "text"class = "form-control-sm" id="ProName"
			name="Proname" value="${ExisistingData.proname }" required>
		</div>
			
		<div class="form-group">
			<label class = "font-italic font-weight-bold"
			for="proId">Product Price:</label>
			<input type = "text"class = "form-control-sm" id="ProPrice"
			name="ProPrice" value="${ExisistingData.proPrice }" required>
		</div>
			
		<div class="form-group">
			<label class = "font-italic font-weight-bold"
			for="proId">Product Brand:</label>
			<input type = "text"class = "form-control-sm" id="Probrand"
			name="ProBrand" value="${ExisistingData.proBrand }" required>
		</div>
			
		<div class="form-group">
			<label class = "font-italic font-weight-bold"
			for="proId">Product MadeIN:</label>
			<input type = "text"class = "form-control-sm" id="ProMadeIN"
			name="ProMadeIN" value="${ExisistingData.proMadein }" required>
		</div>
		
		<div class="form-group">
			<label class = "font-italic font-weight-bold"
			for="proId">Product MFD:</label>
			<input type = "date"class = "form-control-sm" id="ProMfg"
			name="ProMFDdate" value="${ExisistingData.proMfgDate }" required>
		</div>
		
		<div class="form-group">
			<label class = "font-italic font-weight-bold"
			for="proId">Product Exp date:</label>
			<input type = "date"class = "form-control-sm" id="ProExp"
			name="ProExpDate" value="${ExisistingData.proExpDate }" required>
		</div>      
		   
  		<div class="form-group">
			<label class="font-italic font-weight-bold" for="proImage">Current Product 
			Image:</label>
 			<img id="currentImage"
 			src="data:image/jpeg;base64, ${Base64.getEncoder().encodeToString(ExisistingData.imageWork)}"
			alt="Current Product Image" width="100px;">	
			<input
						type="hidden" id="oldProImage" name="oldProImage"
						value="${Base64.getEncoder().encodeToString(ExisistingData.imageWork)}">
			</div>
			
			<div>
			 <input
					type="hidden" id="oldProAudio" name="oldProAudio"
					value="${Base64.getEncoder().encodeToString(ExisistingData.proAudio)}">
			</div>
			<div>
			 <input
					type="hidden" id="oldProVideo" name="oldProVideo"
					value="${Base64.getEncoder().encodeToString(ExisistingData.proVideo)}">
			</div>



			<div class="form-group">
				<label class="font-italic font-weight-bold" for="newProImage">New
					Product Image:</label> <input type="file" class="form-control-file-sm"
					name="proImage" id="proImage" accept="image/*">
			</div>
		
		<div class="form-group">		
            <input type="submit" class="btn btn-success" value="Update Product"/>
        </div>        
      </form>	
</div>

	

</body>
</html>