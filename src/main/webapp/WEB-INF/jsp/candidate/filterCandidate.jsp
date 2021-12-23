<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container border">
	<form action="/filter-candidate-age" method="get">
		<p>
			<u><strong>Chọn tuổi</strong></u>
		</p>
		<div class="row pb-2">
			<div class="col-sm-1">
				<label for="minAge" class="form-label">Từ</label>
			</div>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="minAge" id="minAge">
			</div>
			<div class="col-sm-1">
				<label for="maxAge" class="form-label">Đến</label>
			</div>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="maxAge" id="maxAge">
			</div>
			
			<div class="col-sm-4">
				<button type="submit" class="btn btn-primary">Áp dụng</button>
			</div>
		</div>
		
	</form>
</div>