package sample;


public class TabHistory extends  History {
    private int currentPage;

    TabHistory(){
        currentPage = 0;
    }
    public void addUrl(String url){  // add a page
        if(url != null) {
            if (!atLast()) {
                History tempHistory = new History();
                currentPage++;
                add(url, currentPage - 1);
                tempHistory.deepCopy(getHistory(), currentPage);
                setHistory(tempHistory);
                currentPage = getSize();
            } else {
                add(url);
                currentPage++;
            }
        }
    }
    public void reset(){
        currentPage = 0;
        clear();
    }
    public boolean isEmpty(){return currentPage == 0;}


    public void back(){  //go back to the  previous page if it is not the first page
            currentPage--;
           // text.setText(getCurrentPage());
    }
    public void forward(){  //go forward to the next page if it is not the the last page
            currentPage++;
            //text.setText(getCurrentPage());
    }

    public String getCurrentPage() {
        return getHistoryNodeAt(currentPage-1).getUrl();
    }
    public boolean atFirst(){ // return whether is the current page is the first page
        return currentPage == 1;
    }

    public boolean atLast(){  // return whether is the current page is the last page
        return currentPage == getSize();
    }
    public int getNum(){
        return currentPage;
    }


}