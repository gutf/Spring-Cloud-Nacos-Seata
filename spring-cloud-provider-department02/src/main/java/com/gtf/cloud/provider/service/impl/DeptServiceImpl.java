package com.gtf.cloud.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gtf.cloud.common.vo.DeptUpdateQo;
import com.gtf.cloud.provider.po.Dept;
import com.gtf.cloud.provider.service.DeptService;
import com.gtf.cloud.provider.mapper.DeptMapper;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author GTF
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService{

    private final DeptMapper deptMapper;

    @Override
    public Dept getDeptById(Long id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void updateDept(DeptUpdateQo deptUpdateQo) {
        String xid = RootContext.getXID();
        log.error("xid>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+xid);
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptUpdateQo,dept);
        dept.setId(deptUpdateQo.getDeptId());
        deptMapper.updateById(dept);
    }
}




