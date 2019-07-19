pragma solidity ^0.4.25;

import "./Table.sol";

contract Transation {
    // event
    event RegisterEvent(int256 ret, string orderId, string transation_value);

    constructor() public {
        // 构造函数中创建t_asset表
        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001); 
        // 资产管理表, key : account, field : asset_value
        // |  资产账户(主键)      |     资产金额       |
        // |-------------------- |-------------------|
        // |        account      |    asset_value    |     
        // |---------------------|-------------------|
        //
        // 创建表
        tf.createTable("t_transation", "orderId", "transation_value");
    }

    function openTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_transation");
        return table;
    }

    /*
    描述 : 根据资产账户查询资产金额
    参数 ： 
            account : 资产账户

    返回值：
            参数一： 成功返回0, 账户不存在返回-1
            参数二： 第一个参数为0时有效，资产金额
    */
    function select(string orderId) public constant returns(int256, string) {
        // 打开表
        Table table = openTable();
        // 查询
        Entries entries = table.select(orderId, table.newCondition());
//        string storage asset_value = "null";
//        if (0 == uint256(entries.size())) {
//            return (-1, asset_value);
//        } else {
        Entry entry = entries.get(0);
        return (0, (entry.getString("transation_value")));
//        }
    }

    /*
    描述 : 资产注册
    参数 ： 
            account : 资产账户
            amount  : 资产金额
    返回值：
            0  资产注册成功
            -1 资产账户已存在
            -2 其他错误
    */
//    function register(string account, string asset_value) public returns(int256){
//        int256 ret_code = 0;
//        int256 ret= 0;
//        string storage temp_asset_value= "null";
//        // 查询账号是否存在
//        (ret, temp_asset_value) = select(account);
//        if(ret != 0) {
//            Table table = openTable();
//
//            Entry entry = table.newEntry();
//            entry.set("account", account);
//            entry.set("asset_value", asset_value);
//            // 插入
//            int count = table.insert(account, entry);
//            if (count == 1) {
//                // 成功
//                ret_code = 0;
//            } else {
//                // 失败? 无权限或者其他错误
//                ret_code = -2;
//            }
//        } else {
//            // 账户已存在
//            ret_code = -1;
//        }
//
//        emit RegisterEvent(ret_code, account, asset_value);
//
//        return ret_code;
//    }
    //insert records
    function insert(string orderId, string transation_value) public returns(int) {
        Table table = openTable();
        Entry entry = table.newEntry();
        entry.set("orderId", orderId);
        entry.set("transation_value", transation_value);

        int ret_code = table.insert(orderId, entry);
        emit RegisterEvent(ret_code, orderId, transation_value);

        return ret_code;
    }


}
