<div>
    <h4>基本信息和判断</h4>
    <br>
    name:${name}
    <br>
    age:${age}
    <br>
    sex:<#if sex=="1">
        男性
        <#elseif sex=="2">
        女性
        <#else>
        未知
        </#if>
</div>

<div>
    <h4>循环</h4>
    <br>
    <#if list1??>
        <#if list1? size==0>

        <#else>
            <#list list1 as map>
                <#list map?keys as itemKey>
                    <p>key: ${itemKey} --- value: ${map[itemKey]}</p><br>
                </#list>
            </#list>
        </#if>
    <#else>

    </#if>
</div>