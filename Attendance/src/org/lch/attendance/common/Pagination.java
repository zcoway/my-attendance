package org.lch.attendance.common;

public class Pagination {  
  
   private int rowTotal;// 总记录数  
   private int pageSize = 3;// 每页记录数  
  
   private int page;// 当前页码  
     
   private int total;// 总页数  
   private int beginIndex;//起始记录下标  
   private int endIndex;//截止记录下标  
 
   /** 
    * 使用总记录数、当前页码构造 
    *  
    * @param rowTotal 
    * @param count 
    *            页码，从1开始 
    */  
   public Pagination(int totalRow, int count) {  
       this.rowTotal = totalRow;  
       this.page = count;  
       calculate();  
   }  
 
   /** 
    * 使用总记录数、当前页码和每页记录数构造 
    *  
    * @param rowTotal 
    * @param count 
    *            页码，从1开始 
    * @param pageSize 
    *            默认2条 
    */  
   public Pagination(int totalRow, int page, int pageSize) {  
       this.rowTotal = totalRow;  
       this.page = page;  
       this.pageSize = pageSize;  
       calculate();  
   }  
 
   private void calculate() {  
       total = rowTotal / pageSize + ((rowTotal % pageSize) > 0 ? 1 : 0);  
 
       if (page > total) {  
           page = total;  
       } else if (page < 1) {  
           page = 1;  
       }  
 
       beginIndex = (page - 1) * pageSize +1;  
       endIndex = beginIndex + pageSize-1;  
       if (endIndex > rowTotal) {  
           endIndex = rowTotal;  
       }  
   }  
 
   public int getCurrentPage() {  
       return page;  
   }  
 
   public int getTotal() {  
       return total;  
   }  
 
   public int getTotalRow() {  
       return rowTotal;  
   }  
 
   public int getPageSize() {  
       return pageSize;  
   }  
 
   public int getBeginIndex() {  
       return beginIndex;  
   }  
 
   public int getEndIndex() {  
       return endIndex;  
   }  
   public int getPrePage(){
	   return (this.getBeginIndex()- this.getPageSize())/this.getPageSize()+1 ;
   }
   
   public int getNextPage(){
	   return (this.getBeginIndex()+ this.getPageSize())/this.getPageSize()+1 ;
   }
 
}  