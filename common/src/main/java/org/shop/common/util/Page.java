package org.shop.common.util;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;

@Data
@ToString
public class Page<VO> {
    private long offset;
    private long totalPages;
    private long totalElements;
    private long currentPage;
    private int pageSize;
    private boolean isEmpty;
    private boolean first;
    private boolean last;
    private String orderBy;
    private List<VO> items;


    public static <VO> Page<VO> of(Integer currentPage, Integer pageSize, String orderBy) {
        Page<VO> page = new Page<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage == null || currentPage < 0) ? 0 : currentPage);
        page.setOrderBy(orderBy);
        page.setOffset(page.getPageSize() * page.getCurrentPage());
        return page;
    }

    public static <VO> Page<VO> createFrom(Page source, long totalElements, List<VO> items) {
        Page target = new Page<>();
        BeanUtils.copyProperties(source, target);
        target.setTotalElements(totalElements);
        target.setTotalPages(((totalElements + target.getPageSize() - 1) / target.getPageSize()));
        target.setCurrentPage(target.getCurrentPage() <= 0 ? 0l : target.getCurrentPage() >= target.getTotalPages() ? target.getTotalPages() - 1 : target.getCurrentPage());
        target.setFirst(target.getCurrentPage() == 0);
        target.setLast(target.getCurrentPage() + 1 == target.getTotalPages());
        target.setEmpty(items.size() == 0);
        target.setItems(items);
        return target;
    }

    public static <VO> Page<VO> createSinglePage(List<VO> items) {
        Page page = new Page<>();
        page.setCurrentPage(0);
        page.setPageSize(items.size());
        page.setEmpty(items.isEmpty());
        page.setOffset(0);
        page.setItems(items);
        page.setFirst(true);
        page.setTotalElements(items.size());
        page.setTotalPages(1);
        page.setLast(true);
        return page;
    }

    public static <VO> Page<VO> emptyPage() {
        Page<VO> target = new Page<>();
        target.setItems(Collections.emptyList());
        target.setTotalElements(0);
        target.setTotalPages(0);
        target.setCurrentPage(0);
        target.setOffset(0);
        target.setFirst(true);
        target.setLast(true);
        return target;
    }

}

