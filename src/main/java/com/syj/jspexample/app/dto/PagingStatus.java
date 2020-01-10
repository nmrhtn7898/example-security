package com.syj.jspexample.app.dto;

import lombok.Data;

@Data
public class PagingStatus {

    private int page;

    private int pageSize;

    private int blockSize;

    private int currentBlock;

    private int endBlock;

    private int startPage;

    private int endPage;

    private long cnt;

    private long totalCnt;

    private int totalPage;


    public PagingStatus(Paging paging, long totalCnt, long cnt) {
        this.page = paging.getPage();
        this.pageSize = paging.getPageSize();
        this.blockSize = paging.getBlockSize();
        this.cnt = cnt;
        this.totalCnt = totalCnt;
        this.totalPage = (int) (totalCnt / pageSize);
        this.totalPage = totalCnt % pageSize == 0 ? totalPage : ++totalPage;
        this.currentBlock = page / blockSize;
        this.currentBlock = page % blockSize == 0 ? currentBlock : ++currentBlock;
        this.endBlock = totalPage / blockSize;
        this.endBlock = totalPage % blockSize == 0 ? endBlock : ++endBlock;
        this.startPage = currentBlock * blockSize - (blockSize -1);
        this.endPage = currentBlock == endBlock ? totalPage : startPage + (blockSize - 1);
    }
}
