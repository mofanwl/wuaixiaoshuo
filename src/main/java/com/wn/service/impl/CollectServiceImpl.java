package com.wn.service.impl;

import com.wn.dao.CollectDao;
import com.wn.pojo.Collect;
import com.wn.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/12/6.
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectDao collectDao;
    @Override
    public int insUserOne(Collect collect) {
        return collectDao.insUserOne(collect);
    }

    @Override
    public List<Collect> selListByuser(Integer user_id) {
        return collectDao.selListByuser(user_id);
    }
}
