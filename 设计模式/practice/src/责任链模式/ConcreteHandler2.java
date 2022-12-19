package 责任链模式;

public class ConcreteHandler2 extends Handler{
    public ConcreteHandler2(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.type2) {
            System.out.println(request.getName() + " is handle by ConcreteHandler2");
            return;
        }
        //如果抽象中的对象不为空，说明上面的if没有执行，责任链继续调用
        if (successor != null) {
            successor.handleRequest(request);
        }
    }

}
