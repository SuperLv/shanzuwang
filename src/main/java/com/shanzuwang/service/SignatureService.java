package com.shanzuwang.service;

import com.shanzuwang.bean.req.bill.esignature.*;
import com.shanzuwang.util.http.esignature.DefineException;

/**
 * Created by Hw
 * 20/05/28 16:01
 */
public interface SignatureService {

    public String CREATE_SIGN() throws DefineException;

}
