package examples.web;

import javax.inject.Singleton;

import org.seasar.doma.jdbc.tx.TransactionManager;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import dagger.Component;
import examples.DataSourceModule;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;
import examples.entity.Employee;
import ninja.siden.App;
import ninja.siden.Config;
import ninja.siden.Renderer;
import ninja.siden.RoutingCustomizer;

/**
 * @author taichi
 */
@Singleton
@Component(modules = { DataSourceModule.class, DaoModule.class })
public abstract class ApiServer extends App {

	abstract TransactionManager tm();

	abstract EmployeeDao dao();

	ObjectMapper mapper = new ObjectMapper().configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

	public ApiServer() {
		super(Config.defaults().set(Config.SIDEN_FAVICON, true).getMap());
	}

	public void configure() {
		json(get("/emp/:id", (req, res) -> req.params("id").map(this::byId)));
	}

	// service method
	Employee byId(String id) {
		return tm().required(() -> dao().selectById(Integer.valueOf(id)));
	}

	void json(RoutingCustomizer route) {
		route.render(renderer()).type("application/json");
	}

	<M> Renderer<M> renderer() {
		return Renderer.of((m, w) -> mapper.writeValue(w, m));
	}

	// setup sample database
	public void initialize() {
		tm().required(dao()::create);
	}

	// cleanup sample database
	public void dispose(App app) {
		tm().required(dao()::drop);
	}

	public static void main(String[] args) {
		ApiServer app = DaggerApiServer.create();

		app.initialize();
		app.stopOn(app::dispose);
		app.configure();
		app.listen().addShutdownHook();
	}
}
