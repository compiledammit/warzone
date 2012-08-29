package recordstore

import static org.junit.Assert.*
import org.junit.*

class ModelRelationshipTests {

// NOTE: this one will fail due do eventual addition of belongsTo
/*
@Test
void songShouldHaveAnAlbum() {
	// Create and save an album
	def album = new Album( name: "Emotionalism" ).save()
	
	// Create a song without an album: shoudn't be valid
	def song = new Song( name: "Salvation Song" )
	
	// Shouldn't validate, shouldn't have an id
	assert !song.validate()
	assert !song.id
	
	// Set its album: should validate and save
	song.album = album
	assert song.validate()
	song.save()
	assert song.id
}
*/

@Test
void albumHasManySongShouldCascadeSaves() {
	def originalSongCount = Song.list().size()
	
	// Create and save an album
	def album = new Album( name: "Emotionalism" ).save()
	
	// Create a song without an album: shoudn't be valid
	def song = new Song( name: "Salvation Song" )
	
	album.addToSongs( song )

	// References should be set up
	assert song.album == album
	assert album.songs.contains( song )
	
	// Saving album should save song
	assert !song.id
	album.save()
	assert Song.list().size() == originalSongCount + 1
	assert song.id
}

@Test
void settingSongsAlbumDoesntAddToAlbumsSongs() {
	// Create and save an album
	def album = new Album( name: "Emotionalism" ).save()
	
	// Create a song without an album: shoudn't be valid
	def song = new Song( name: "Salvation Song" )
	
	song.album = album

	// Back references should not be set up
	assert song.album == album
	assert album.songs == null
}

@Test
void songBelongsToAlbumShouldCascadeDeletes() {
	def originalSongCount = Song.list().size()
	
	// Create and save an album
	def album = new Album( name: "Emotionalism" )
	def song = new Song( name: "Salvation Song" )
	
	album.addToSongs( song )
	
	// Saving an album should also save its songs
	assert !album.id
	assert !song.id
	album.save()
	assert album.id
	assert song.id
	
	// Deleting an album should delete its songs
	album.delete()
	assert Song.list().size() == originalSongCount
}

@Test
void songsWithinAnAlbumAreOrdered() {
	def originalSongCount = Song.list().size()
	
	// Create and save an album
	def album = new Album( name: "Emotionalism" )
	def song = new Song( name: "Shame" )
	def song2 = new Song( name: "Die Die Die" )
	
	album.addToSongs( song )
	album.addToSongs( song2 )
	
	// Lists have an innate and reliable order
	def tmp = album.songs.get( 0 )
	album.songs[0] = album.songs[1]
	album.songs[1] = tmp
	
	assert album.songs[0] == song2
	assert album.songs[1] == song
	
}

@Test
void weCanQueryForSongsViaFindersOrHQL() {
	// Create and save an album
	def album = new Album( name: "Emotionalism" )
	def song = new Song( name: "Die Die Die" )
	def song2 = new Song( name: "Shame" )
	
	album.addToSongs( song )
	album.addToSongs( song2 )
	album.save()
	
	// Dynamic finder
	assert Song.findAllByAlbum( album ).size() == 2
	
	// HQL
	assert Song.executeQuery( "from Song s where s.album = ?", [ album ] ).size() == 2
}

/* Starts failing when many-to-many is added because Artist must belong-to Album
@Test
void artistHasOneBiographyShouldCascadeSaves() {
	def originalBiographyCount = Biography.list().size()
	
	// Create a bio
	def bio = new Biography( content: "Two guys and friends from Concord, NC")
	
	// Create an artist
	def artist = new Artist( name: "The Avett Brothers", biography : bio )
	
	// References should be set up
	assert artist.biography == bio
	assert bio.artist == artist
	
	// Saving album should save song
	assert !bio.id
	artist.save()
	assert Biography.list().size() == originalBiographyCount + 1
	assert bio.id
}
*/

@Test
void artistIsManyToManyAlbum() {
	// Note that we save from the 'owning' side!
	def originalArtistCount = Artist.list().size()
	
	def album = new Album( name : "Chimes of Freedom")
		.addToArtists( new Artist( name : "The Avett Brothers", biography : new Biography( content: "Two guys etc." ) ) )
		.addToArtists( new Artist( name : "Johnny Cash", biography : new Biography( content: "A man in 0x000000." ) ) )
		.save()
		
	assert album.id
	assert Artist.list().size() == originalArtistCount + 2
		
}


}



