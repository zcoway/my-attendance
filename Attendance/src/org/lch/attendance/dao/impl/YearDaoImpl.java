package org.lch.attendance.dao.impl;

import org.lch.attendance.dao.YearDao;
import org.lch.attendance.domain.Year;
import org.springframework.stereotype.Repository;
@Repository("yearDao")
public class YearDaoImpl extends GenericDaoImpl<Year> implements YearDao {

}
