/**
 * 
 */
function buttun1() {

	if(document.frm2.subject.value==""){
		alert("제목을 입력하세요.");
		document.frm2.subject.focus();
	}else if(document.frm2.content.value==""){
		alert("내용을 입력하세요.");
		document.frm2.content.focus();
	}else{
		document.frm2.submit();
	}
}