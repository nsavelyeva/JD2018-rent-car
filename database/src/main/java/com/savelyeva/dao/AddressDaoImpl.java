package com.savelyeva.dao;

import com.savelyeva.model.Address;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AddressDaoImpl extends BaseDaoImpl<Long, Address> implements AddressDao {

}
