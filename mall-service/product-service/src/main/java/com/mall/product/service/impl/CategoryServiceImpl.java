package com.mall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.product.dto.request.CategoryCreateRequest;
import com.mall.api.product.dto.response.CategoryVO;
import com.mall.common.constant.CommonConstants;
import com.mall.common.result.Result;
import com.mall.common.utils.Assert;
import com.mall.product.entity.ProductCategory;
import com.mall.product.mapper.ProductCategoryMapper;
import com.mall.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Category Service Implementation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ProductCategoryMapper categoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "category", allEntries = true)
    public Result<CategoryVO> createCategory(CategoryCreateRequest request) {
        // Check if category code exists
        Long count = categoryMapper.selectCount(new LambdaQueryWrapper<ProductCategory>()
                .eq(ProductCategory::getCategoryCode, request.getCategoryCode()));
        Assert.isTrue(count == 0, "Category code already exists");

        ProductCategory category = new ProductCategory();
        BeanUtil.copyProperties(request, category);
        categoryMapper.insert(category);

        CategoryVO vo = new CategoryVO();
        BeanUtil.copyProperties(category, vo);
        return Result.success(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "category", allEntries = true)
    public Result<CategoryVO> updateCategory(Long categoryId, CategoryCreateRequest request) {
        ProductCategory category = categoryMapper.selectById(categoryId);
        Assert.notNull(category, "Category not found");

        BeanUtil.copyProperties(request, category, "id", "createTime", "createBy");
        categoryMapper.updateById(category);

        CategoryVO vo = new CategoryVO();
        BeanUtil.copyProperties(category, vo);
        return Result.success(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "category", allEntries = true)
    public Result<Void> deleteCategory(Long categoryId) {
        // Check if category has children
        Integer childCount = categoryMapper.hasChildren(categoryId);
        Assert.isTrue(childCount == 0, "Category has children, cannot delete");

        // Check if category has products
        Integer productCount = categoryMapper.hasProducts(categoryId);
        Assert.isTrue(productCount == 0, "Category has products, cannot delete");

        int result = categoryMapper.deleteById(categoryId);
        Assert.isTrue(result > 0, "Failed to delete category");

        return Result.success();
    }

    @Override
    public Result<CategoryVO> getCategoryById(Long categoryId) {
        ProductCategory category = categoryMapper.selectById(categoryId);
        if (category == null) {
            return Result.failed("Category not found");
        }

        CategoryVO vo = new CategoryVO();
        BeanUtil.copyProperties(category, vo);
        return Result.success(vo);
    }

    @Override
    @Cacheable(value = "category", key = "'tree'")
    public Result<List<CategoryVO>> getCategoryTree() {
        List<CategoryVO> allCategories = categoryMapper.selectCategoryTree(null);
        List<CategoryVO> tree = buildTree(allCategories, CommonConstants.TREE_ROOT_ID);
        return Result.success(tree);
    }

    @Override
    public Result<List<CategoryVO>> getCategoriesByParentId(Long parentId) {
        List<CategoryVO> categories = categoryMapper.selectCategoryTree(parentId);
        return Result.success(categories);
    }

    @Override
    public Result<List<CategoryVO>> getCategoryPath(Long categoryId) {
        List<CategoryVO> path = categoryMapper.selectCategoryPath(categoryId);
        return Result.success(path);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "category", allEntries = true)
    public Result<Void> updateCategoryStatus(Long categoryId, Integer status) {
        ProductCategory category = new ProductCategory();
        category.setId(categoryId);
        category.setStatus(status);
        int result = categoryMapper.updateById(category);
        
        Assert.isTrue(result > 0, "Failed to update category status");
        return Result.success();
    }

    private List<CategoryVO> buildTree(List<CategoryVO> categories, Long parentId) {
        Map<Long, List<CategoryVO>> childrenMap = categories.stream()
                .collect(Collectors.groupingBy(CategoryVO::getParentId));
        
        List<CategoryVO> tree = childrenMap.getOrDefault(parentId, new ArrayList<>());
        tree.forEach(node -> node.setChildren(buildTree(categories, node.getId())));
        
        return tree;
    }
}
