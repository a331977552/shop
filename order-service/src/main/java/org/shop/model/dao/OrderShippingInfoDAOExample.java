package org.shop.model.dao;

import java.util.ArrayList;
import java.util.List;

public class OrderShippingInfoDAOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderShippingInfoDAOExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTrackingNumIsNull() {
            addCriterion("tracking_num is null");
            return (Criteria) this;
        }

        public Criteria andTrackingNumIsNotNull() {
            addCriterion("tracking_num is not null");
            return (Criteria) this;
        }

        public Criteria andTrackingNumEqualTo(String value) {
            addCriterion("tracking_num =", value, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumNotEqualTo(String value) {
            addCriterion("tracking_num <>", value, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumGreaterThan(String value) {
            addCriterion("tracking_num >", value, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumGreaterThanOrEqualTo(String value) {
            addCriterion("tracking_num >=", value, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumLessThan(String value) {
            addCriterion("tracking_num <", value, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumLessThanOrEqualTo(String value) {
            addCriterion("tracking_num <=", value, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumLike(String value) {
            addCriterion("tracking_num like", value, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumNotLike(String value) {
            addCriterion("tracking_num not like", value, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumIn(List<String> values) {
            addCriterion("tracking_num in", values, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumNotIn(List<String> values) {
            addCriterion("tracking_num not in", values, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumBetween(String value1, String value2) {
            addCriterion("tracking_num between", value1, value2, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andTrackingNumNotBetween(String value1, String value2) {
            addCriterion("tracking_num not between", value1, value2, "trackingNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdIsNull() {
            addCriterion("delivery_company_id is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdIsNotNull() {
            addCriterion("delivery_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdEqualTo(Integer value) {
            addCriterion("delivery_company_id =", value, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdNotEqualTo(Integer value) {
            addCriterion("delivery_company_id <>", value, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdGreaterThan(Integer value) {
            addCriterion("delivery_company_id >", value, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_company_id >=", value, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdLessThan(Integer value) {
            addCriterion("delivery_company_id <", value, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_company_id <=", value, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdIn(List<Integer> values) {
            addCriterion("delivery_company_id in", values, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdNotIn(List<Integer> values) {
            addCriterion("delivery_company_id not in", values, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("delivery_company_id between", value1, value2, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_company_id not between", value1, value2, "deliveryCompanyId");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameIsNull() {
            addCriterion("delivery_company_name is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameIsNotNull() {
            addCriterion("delivery_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameEqualTo(String value) {
            addCriterion("delivery_company_name =", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameNotEqualTo(String value) {
            addCriterion("delivery_company_name <>", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameGreaterThan(String value) {
            addCriterion("delivery_company_name >", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_company_name >=", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameLessThan(String value) {
            addCriterion("delivery_company_name <", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("delivery_company_name <=", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameLike(String value) {
            addCriterion("delivery_company_name like", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameNotLike(String value) {
            addCriterion("delivery_company_name not like", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameIn(List<String> values) {
            addCriterion("delivery_company_name in", values, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameNotIn(List<String> values) {
            addCriterion("delivery_company_name not in", values, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameBetween(String value1, String value2) {
            addCriterion("delivery_company_name between", value1, value2, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameNotBetween(String value1, String value2) {
            addCriterion("delivery_company_name not between", value1, value2, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andSOrderIdIsNull() {
            addCriterion("s_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSOrderIdIsNotNull() {
            addCriterion("s_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSOrderIdEqualTo(String value) {
            addCriterion("s_order_id =", value, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdNotEqualTo(String value) {
            addCriterion("s_order_id <>", value, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdGreaterThan(String value) {
            addCriterion("s_order_id >", value, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("s_order_id >=", value, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdLessThan(String value) {
            addCriterion("s_order_id <", value, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdLessThanOrEqualTo(String value) {
            addCriterion("s_order_id <=", value, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdLike(String value) {
            addCriterion("s_order_id like", value, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdNotLike(String value) {
            addCriterion("s_order_id not like", value, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdIn(List<String> values) {
            addCriterion("s_order_id in", values, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdNotIn(List<String> values) {
            addCriterion("s_order_id not in", values, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdBetween(String value1, String value2) {
            addCriterion("s_order_id between", value1, value2, "sOrderId");
            return (Criteria) this;
        }

        public Criteria andSOrderIdNotBetween(String value1, String value2) {
            addCriterion("s_order_id not between", value1, value2, "sOrderId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}