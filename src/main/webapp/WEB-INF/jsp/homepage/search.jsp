<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="/homepage">

	<div class="d-flex align-items-end justify-content-between">
		<div>
			Tìm kiếm <input type="text" class="form-control" id="search"
				name="email">
		</div>
		
		<div>
			Sắp xếp theo:<select class="form-select" id="sel1" name="sellist1">
				<option>Công nghệ</option>
				<option>Ngôn ngữ lập trình</option>
				<option>Nhành nghề</option>
				<option>Mới nhất</option>
			</select>
		</div>
		
		<div>
			<button type="submit" class="btn btn-primary">Tìm kiếm</button>
		</div>
	</div>


</form>