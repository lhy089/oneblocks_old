<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script id="searchTemplate" type="x-tmpl-mustache">
 {{#searchParam}}
	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
      </ul>
      <form class="d-flex a" role="search" action="/campaign/main" method="post" id="searchForm"> 
      	<i class="fa-solid fa-angle-left fa-2x search-element" onclick="campaignMainListBySearch('prev')" style="cursor:pointer;"></i>
      	<select id="dateFlag" name="dateFlag" onchange="campaignMainListBySearch('select')" size="1" class="search-element">
      		{{#options}}
				<option value="{{val}}" {{#sel}}selected{{/sel}}>{{txt}}</option>
			{{/options}}
		</select>
		<input type="hidden" id="flag" name="flag" value="{{flag}}"/>
      	<input type="date" id="startDate" name="startDate" max="9999-12-31" class="datepicker form-control search-element" value="{{startDate}}"> 
      	<label class="search-element">~</label> 
      	<input type="date" id="endDate" name="endDate" max="9999-12-31" class="datepicker form-control search-element" value="{{endDate}}">
	

		<i class="fa-solid fa-angle-right fa-2x" onclick="campaignMainListBySearch('next')" style="cursor:pointer;" ></i>
      </form>
  {{/searchParam}}
</script>
