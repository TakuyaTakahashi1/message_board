package controllers;

//id はMySQLの auto_increment の採番
//title と content はフォームから入力された内容をセット

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;


@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public CreateServlet() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");

        //CSRF対策のチェック
        //_token に値がセットされていなかったりセッションIDと値が異なったりしたらデータの登録ができないようにしています
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            Message m = new Message();

            String title = request.getParameter("title");
            m.setTitle(title);

            String content = request.getParameter("content");
            m.setContent(content);

            //このように記述することで現在日時の情報を持つ日付型のオブジェクトを取得できます（Javaでは日時情報もオブジェクトで管理します）。
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);

            //必要な情報をセットした Message クラスのオブジェクトを persist メソッドを使ってデータベースにセーブ
            em.persist(m);

            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "登録が完了しました。");
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}