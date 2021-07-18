package Block3;

import mm.example.Block3.Polyhedrons;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

public class PolyhedronsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTotalNumberOfFaces() {
        List<String> polyhedrons = new ArrayList<>();
        polyhedrons.add("Dodecahedron");
        polyhedrons.add("Octahedron");
        polyhedrons.add("Octahedron");

        int expected = 28;

        Assert.assertEquals(expected, Polyhedrons.calculateTotalNumberOfPolyhedronsFaces(polyhedrons));
    }

    @Test
    public void shouldReturnExceptionForInvalidNumberOfFaces() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(equalTo("The number of polyhedrons must be an integer."));
        Polyhedrons.convertNumberOfPolyhedrons("MARI");
    }

    @Test
    public void shouldReturnIntegerNumberOfFaces() throws Exception {
        Assert.assertEquals(3, Polyhedrons.convertNumberOfPolyhedrons("3"));
    }

    @Test
    public void shouldReturnExceptionForInvalidPolyhedronName() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(equalTo("The polyhedron name should be in [Tetrahedron, Cube, Octahedron, Dodecahedron, Icosahedron]"));
        Polyhedrons.validateNamePolyhedron("MARI");
    }

    @Test
    public void shouldReturnTrueForPolyhedronName() throws Exception {
        Assert.assertTrue(Polyhedrons.validateNamePolyhedron("Dodecahedron"));
        Assert.assertTrue(Polyhedrons.validateNamePolyhedron("Tetrahedron"));
        Assert.assertTrue(Polyhedrons.validateNamePolyhedron("Cube"));
        Assert.assertTrue(Polyhedrons.validateNamePolyhedron("Octahedron"));
        Assert.assertTrue(Polyhedrons.validateNamePolyhedron("Icosahedron"));
    }

}